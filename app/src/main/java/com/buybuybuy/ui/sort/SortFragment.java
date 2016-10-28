package com.buybuybuy.ui.sort;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buybuybuy.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SortFragment extends Fragment implements View.OnClickListener {
    private List<KindMain> kindMains;

    public SortFragment() {
    }
    private static SortFragment sortFragment;
    public  static Fragment setFragment(){
        if (sortFragment==null)
            synchronized (SortFragment.class){
                if (sortFragment==null){
                    sortFragment=new SortFragment();
                }
            }
        return sortFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_main_sale, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentManager().beginTransaction().add(R.id.main_right_show,new DetailsMainFragment()).commit();
        initView(view);
        new Thread(){
            @Override
            public void run() {
                Request request = new Request.Builder().get().url(Contasts.KIND_MAIN).build();
                new OkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            try {
                                byte[] datas = response.body().bytes();
                                String json = new String(datas);
                                JSONObject object = new JSONObject(json);
                                String jsons = object.getString("action");
                                Gson gson = new Gson();
                                kindMains = gson.fromJson(jsons, new TypeToken<List<KindMain>>() {
                                }.getType());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }.start();
    }

    private void initView(View view) {
        view.findViewById(R.id.tv_taobao).setOnClickListener(this);
        view.findViewById(R.id.juhuasuan).setOnClickListener(this);
        view.findViewById(R.id.taoqianggou).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_taobao:
                Intent intent = new Intent(getActivity(), TaoBaoActivity.class);
                String taoBao = kindMains.get(0).getData();
                intent.putExtra("path",taoBao);
                startActivity(intent);
                break;
            case R.id.juhuasuan:
                Intent intent1 = new Intent(getActivity(), TaoBaoActivity.class);
                String juhuasuan = kindMains.get(1).getData();
                intent1.putExtra("path",juhuasuan);
                startActivity(intent1);
                break;
            case R.id.taoqianggou:
                Intent intent2 = new Intent(getActivity(), TaoBaoActivity.class);
                String qianggou = kindMains.get(2).getData();
                intent2.putExtra("path",qianggou);
                startActivity(intent2);
                break;
        }
    }
}
