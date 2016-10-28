package com.buybuybuy.ui.sort;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.buybuybuy.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailGridFragment extends Fragment {

    private PullToRefreshGridView gvPull;
    private List<DetailsGoods> detailsGoodses;
    private GridView gvDetail;
    private String path;
    private LastDetialsAdapters adapters;

    public DetailGridFragment() {
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            detailsGoodses = (List<DetailsGoods>) msg.obj;
            adapters.setDate(detailsGoodses);
            adapters.notifyDataSetChanged();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gvPull = (PullToRefreshGridView) view.findViewById(R.id.gv_ditail);
        gvDetail = gvPull.getRefreshableView();
        adapters = new LastDetialsAdapters( getContext());
        gvDetail.setAdapter(adapters);
        gvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailsGoods goods = (DetailsGoods) parent.getItemAtPosition(position);
                String url = goods.getUrl();
                Intent intent = new Intent(getActivity(), DetaillastActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        Bundle bundle = getArguments();
        path = bundle.getString("path");
        new Thread() {
            @Override
            public void run() {
                Request request = new Request.Builder().get().url(path).build();
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            try {
                                Message msg = Message.obtain();
                                byte[] datas = response.body().bytes();
                                String json = new String(datas);
                                JSONObject object = new JSONObject(json);
                                String data = object.getString("data");
                                Gson gson = new Gson();
                                detailsGoodses = gson.fromJson(data, new TypeToken<List<DetailsGoods>>() {
                                }.getType());
                                msg.obj = detailsGoodses;
                                mHandler.sendMessage(msg);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }.start();
      pulltoRefresh();
    }

    public void pulltoRefresh() {
        //设置下拉模式
        gvPull.setMode(PullToRefreshBase.Mode.BOTH);
        gvPull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                String labl1 = DateUtils.formatDateTime(getContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(labl1);
                new Thread() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder().get().url(path).build();
                        new OkHttpClient().newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                if (response.isSuccessful()) {
                                    try {
                                        Message msg = Message.obtain();
                                        byte[] datas = response.body().bytes();
                                        String json = new String(datas);
                                        JSONObject object = new JSONObject(json);
                                        String data = object.getString("data");
                                        Gson gson = new Gson();
                                        detailsGoodses = gson.fromJson(data, new TypeToken<List<DetailsGoods>>() {
                                        }.getType());
                                        msg.obj = detailsGoodses;
                                        mHandler.sendMessage(msg);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }.start();
                //刷新完成
                gvPull.onRefreshComplete();
            }

             //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                int size = 50;
                size+=10;
                String sizes = Integer.toString(size);
                final String xialaPath = path.replaceAll("size=50", "size=" + sizes);
                Log.i("TAG", "----------------- "+xialaPath);
                new Thread() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder().get().url(xialaPath).build();
                        new OkHttpClient().newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                if (response.isSuccessful()) {
                                    try {
                                        Message msg = Message.obtain();
                                        byte[] datas = response.body().bytes();
                                        String json = new String(datas);
                                        JSONObject object = new JSONObject(json);
                                        String data = object.getString("data");
                                        Gson gson = new Gson();
                                        detailsGoodses = gson.fromJson(data, new TypeToken<List<DetailsGoods>>() {
                                        }.getType());
                                        msg.obj = detailsGoodses;
                                        mHandler.sendMessage(msg);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }.start();
                //刷新完成
                gvPull.onRefreshComplete();
            }
        });
    }

}
