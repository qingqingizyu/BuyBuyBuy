package com.buybuybuy.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;

import com.buybuybuy.R;
import com.buybuybuy.bean.HomeBean;
import com.buybuybuy.http.OkHttpClientUtils;
import com.buybuybuy.ui.sort.DetaillastActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyHomeFragment extends Fragment {
    private PullToRefreshGridView mPullToRefreshGridView;
    private String path;

        public Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                gridView.setNumColumns(1);
                HomeGridAdapter home = new HomeGridAdapter(getContext());
                home.setHomeData((HomeBean) msg.obj);
                gridView.setAdapter(home);
            }else if (msg.what == 2){
                gridView.setNumColumns(2);
                HomeGridAdapter11 home = new HomeGridAdapter11(getContext());
                home.setHomeData11((HomeBean) msg.obj);
                gridView.setAdapter(home);
            }
        }
    };
    private GridView gridView;
    private CheckBox button;
    private boolean isChanged;

    public MyHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPullToRefreshGridView = (PullToRefreshGridView) view.findViewById(R.id.home_gridView);
        gridView = mPullToRefreshGridView.getRefreshableView();
//        button = (CheckBox) view.findViewById(R.id.home_btn_layout);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeBean.DataBean.GoodsBean homeBean = (HomeBean.DataBean.GoodsBean) parent.getItemAtPosition(position);
                String url = homeBean.getUrl();
                Intent intent = new Intent(getActivity(), DetaillastActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isChanged) {
//                    downloadJsondata(1);
//                    isChanged = false;
//                }else {
//                    downloadJsondata(2);
//                    isChanged = true;
//                    button.setSelected(false);
//                    //   ivButton.setSelected(true);
//                }
//            }
//        });
        initdata(2);
    }
        private void initdata(final int b) {
            Bundle bundle = getArguments();
            path = bundle.getString("pos");
            new Thread(){
                @Override
            public void run() {
                try {
                    //请求数据
                        String data = OkHttpClientUtils.getStringFromUrl(path);
                        HomeBean homeBean = new Gson().fromJson(data, HomeBean.class);
                        Message message = Message.obtain();
                        message.what = b;
                        message.obj = homeBean;
                        mHandler.sendMessage(message);
                    //利用fastjson解析HomeBean
//                    mGoodsBean = JSON.parseArray(str, HomeBean.DataBean.GoodsBean.class);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
