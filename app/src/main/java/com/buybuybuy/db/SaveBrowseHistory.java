package com.buybuybuy.db;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.buybuybuy.bean.HotBean;

/**
 * 保存浏览历史的方法
 * Created by Administrator on 2016/10/14.
 */
//前面的数据穿还没传过来，方法先空着
public class SaveBrowseHistory extends Application {
    SQLiteDatabase mDb;

    private HotBean mList;
    public SaveBrowseHistory(){

    }

    //从集合中取出数据并将数据存入数据库中
    public void save(HotBean list){
        this.mList = list;
        ContentValues values = new ContentValues();
        values.put("img", mList.getImg());     //图片网址链接
        values.put("title",mList.getTitle());     //标题
        values.put("price",mList.getPrice());     //现价
        values.put("original_price",mList.getOriginal_price());     //原价
        values.put("buy_num",mList.getBuy_num());     //售出件数
        values.put("source",mList.getSource());     //卖家
        values.put("url",mList.getUrl());     //点击后跳转页面的链接
        if (!queryName()){
            long id = mDb.insert("bybyby",null,values);
            if (id>0){
                Toast.makeText(SaveBrowseHistory.this, "添加成功", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(SaveBrowseHistory.this, "该条数据已经存在", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean queryName(){
        String queryname = null;
        Cursor cursor = mDb.query("bybyby",new String[]{"title"},"title = ?",new String[]{},null,null,null);
        if (cursor.getCount()>0){
            cursor.close();
            return true;
        }
        return false;
    }

}
