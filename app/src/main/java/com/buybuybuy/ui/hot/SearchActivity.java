package com.buybuybuy.ui.hot;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.buybuybuy.R;
import com.buybuybuy.bean.  SearchBean;
import com.buybuybuy.config.HotConstruct;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements MyAdapter.MyItemClickListener{

    private Message msg;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                //发送消息
                data = (List<SearchBean>) msg.obj;
                MyAdapter adapter = new MyAdapter(data);
                mRecycler.setAdapter(adapter);
                adapter.setOnItemClickListener(SearchActivity.this);
            }
        }
    };
    private List<SearchBean> data;
    private RecyclerView mRecycler;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button btn_search = (Button) findViewById(R.id.btn_search);
        mRecycler = (RecyclerView) findViewById(R.id.recycle_search);
        editText = (EditText) findViewById(R.id.ed_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editText.getText().toString();
                Intent intent = new Intent(SearchActivity.this,SearchWebActivity.class);
                if (query == null){
                    String search = HotConstruct.QUERYPATH + "毛绒玩具";
                    intent.putExtra("url",search);
                }else {
                    String search = HotConstruct.QUERYPATH + query+"";
                    intent.putExtra("url",search);
                }
                startActivity(intent);
            }
        });

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);

        //添加修饰.用来修改每个条目之间的间距.
        mRecycler.addItemDecoration(new MyDecoration(10));
        runList(1);

        mRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
    }

    @Override
    public void onItemClick(View view, int postion) {
        SearchBean searchBean = data.get(postion);
        String url = searchBean.getUrl();
        Intent intent = new Intent(this,SearchWebActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    //Decoration:装饰,美化.
    class MyDecoration extends RecyclerView.ItemDecoration {

        private int mOffset;

        public MyDecoration(int offset) {
            this.mOffset = offset;
        }

        //用来获取每个item的偏移量
        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            super.getItemOffsets(outRect, itemPosition, parent);

            outRect.left = mOffset;
            outRect.bottom = 5;
            outRect.right = 3;
            //outRect.top
            //outRect.right;
        }


    }

    private void runList(final int b) {
        new Thread() {
            @Override
            public void run() {
                msg = Message.obtain();
                data = HotJson.jsonSearch(HotConstruct.SEARCHPATH);
                msg.what = b;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }
        }.start();
    }
}
