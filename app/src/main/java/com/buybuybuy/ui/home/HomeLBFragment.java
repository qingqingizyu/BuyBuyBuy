package com.buybuybuy.ui.home;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.buybuybuy.R;
import com.buybuybuy.bean.HomeBean;
import com.buybuybuy.http.OkHttpClientUtils;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeLBFragment extends Fragment {
    private ListView mListView;

    public Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeGridAdapter home = new HomeGridAdapter(getContext());
            home.setHomeData((HomeBean) msg.obj);
            mListView.setAdapter(home);
        }
    };

    public HomeLBFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_lb, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initdata();
        mListView = (ListView) view.findViewById(R.id.home_listView);
    }
    private void initdata() {
        new Thread(){
            @Override
            public void run() {
                try {
                    //请求数据
                        String data = OkHttpClientUtils.getStringFromUrl(Constants.NvSJX);
                        HomeBean homeBean = new Gson().fromJson(data, HomeBean.class);
                        Message message = Message.obtain();
                        message.what = 1;
                        message.obj = homeBean;
                        mHandler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();

            }
        }.start();
    }

}