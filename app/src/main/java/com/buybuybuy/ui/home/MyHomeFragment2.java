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
import com.buybuybuy.bean.Home2Bean;
import com.buybuybuy.http.OkHttpClientUtils;
import com.buybuybuy.ui.sort.DetaillastActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyHomeFragment2 extends Fragment {
    private PullToRefreshGridView mPullToRefreshGridView;

        public Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
//                HomeGridAdapter home = new HomeGridAdapter(getContext());
//                home.setHomeData((HomeBean) msg.obj);
//                gridView.setAdapter(home);
            }else if (msg.what == 2){
                HomeGridAdapter2 home = new HomeGridAdapter2(getContext());
                home.setHomeData2((Home2Bean) msg.obj);
                gridView.setAdapter(home);
            }
        }
    };
    private String path2;
    private GridView gridView;
    private CheckBox button;
    private boolean isChanged;

    public MyHomeFragment2() {
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
        initdata();
        mPullToRefreshGridView = (PullToRefreshGridView) view.findViewById(R.id.home_gridView);
        gridView = mPullToRefreshGridView.getRefreshableView();
//        button = (CheckBox) view.findViewById(R.id.home_btn_layout);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Home2Bean.DataBean home2Bean = (Home2Bean.DataBean) parent.getItemAtPosition(position);
                String url = home2Bean.getUrl();
                Intent intent = new Intent(getActivity(), DetaillastActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isChanged) {
//                    downloadJsondata();
//                    isChanged = false;
//                }else {
//                    runList();
//                    isChanged = true;
//                    button.setSelected(false);
//                    //   ivButton.setSelected(true);
//                }
//            }
//        });
//        downloadJsondata();
    }

        private void initdata() {
            Bundle bundle = getArguments();
            path2 = bundle.getString("home");
            new Thread(){
                @Override
            public void run() {
                try {
                    //请求数据
                        String data = OkHttpClientUtils.getStringFromUrl(path2);
                        Home2Bean homeBean = new Gson().fromJson(data, Home2Bean.class);
                        Message message = Message.obtain();
                        message.what = 2;
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
