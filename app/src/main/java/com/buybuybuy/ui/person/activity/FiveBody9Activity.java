package com.buybuybuy.ui.person.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.buybuybuy.R;
import com.buybuybuy.db.BybybyDao;

public class FiveBody9Activity extends AppCompatActivity {

    private BybybyDao mDao;
    private SQLiteDatabase mDb;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_five_body9);

/*        mDao = new BybybyDao(this);
        mDb = mDao.getDb();
        mLv = (ListView) findViewById(R.id.list_five_body9);
        assert mLv != null;
        final Cursor cursor = mDb.query("bybyby", new String[]{"_id", "img", "title","price","original_price","buy_num","source"}, null, null, null, null, null);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.list_item_hot_1,cursor,new String[]{"img", "title","price","original_price","buy_num","source"},new int[]{R.id.iv_img,R.id.tv_title,R.id.tv_price,R.id.tv_original_price,R.id.tv_buy_num,R.id.tv_source},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mLv.setAdapter(adapter);

        //点击跳转到详情页面
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor1 = (Cursor) parent.getItemAtPosition(position);
                //先获取url的index
                int urlIndex = cursor1.getColumnIndex("url");
                //再根据index获取对应列的值
                String url = cursor1.getString(urlIndex);
                Toast.makeText(FiveBody9Activity.this,"请打开该链接"+url,Toast.LENGTH_LONG).show();
            }
        });*/
    }
    //点击返回到上一页
    public void collectionBack(View view) {
        finish();
    }

    //点击清除浏览记录里的所有数据
    public void collectionClear(View view) {
        Toast.makeText(FiveBody9Activity.this,"历史记录清理完毕",Toast.LENGTH_LONG).show();
    }
}
