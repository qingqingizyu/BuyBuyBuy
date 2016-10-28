package com.buybuybuy.ui.hot;


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
import android.widget.ImageView;

import com.buybuybuy.R;
import com.buybuybuy.bean.HotBean;
import com.buybuybuy.config.HotConstruct;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.List;


public class HotFragment extends Fragment {

    private List<HotBean> hotBeen;
    private Message msg;
    private PullToRefreshGridView mPullToRefreshGridView;
    private GridView gridView;
    private static HotFragment hotFragment;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                gridView.setNumColumns(1);
                hotBeen = (List<HotBean>) msg.obj;
                HotAdapter adapter = new HotAdapter(getContext());
                adapter.setData(hotBeen);
                mPullToRefreshGridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                gridView.setNumColumns(2);
                hotBeen = (List<HotBean>) msg.obj;
                Hot2Adapter adapter = new Hot2Adapter(getContext());
                adapter.setData(hotBeen);
                mPullToRefreshGridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    };
    private CheckBox button;
    private boolean isChanged = true;
    private ImageView ivButton;

    public HotFragment() {
        // Required empty public constructor
    }

    public static Fragment setFragment() {
        if (hotFragment == null)
            synchronized (HotFragment.class) {
                if (hotFragment == null) {
                    hotFragment = new HotFragment();
                }
            }
        return hotFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //找到控件
        ivButton = (ImageView) view.findViewById(R.id.ivb_hot);
        mPullToRefreshGridView = (PullToRefreshGridView) view.findViewById(R.id.pull_refresh_grid);
        mPullToRefreshGridView.setScrollingWhileRefreshingEnabled(true);
        button = (CheckBox) view.findViewById(R.id.btn_layout);
        ivButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChanged) {
                    runList(2);
                    isChanged = false;
                }else {
                    runList(1);
                    isChanged = true;
                    button.setSelected(false);
                 //   ivButton.setSelected(true);
                }
            }
        });
        //得到gridview，并且设置每行数目
        gridView = mPullToRefreshGridView.getRefreshableView();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HotBean hotBean = hotBeen.get(position);
                String url = hotBean.getUrl();
                Intent intent = new Intent(getActivity(),SearchWebActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);

            }
        });
//        if (isChanged) {
//            gridView.setNumColumns(1);
//        } else {
//            gridView.setNumColumns(2);
//        }
        runList(1);
    }

    private void runList(final int b) {
        new Thread() {
            @Override
            public void run() {
                msg = Message.obtain();
                hotBeen = HotJson.jsonHot(HotConstruct.HOTPATH);
                msg.what = b;
                msg.obj = hotBeen;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
