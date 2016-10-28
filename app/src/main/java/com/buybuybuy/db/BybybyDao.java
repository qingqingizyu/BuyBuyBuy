package com.buybuybuy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/10/14.
 */

public class BybybyDao {
    private final SQLiteDatabase mDb;

    public BybybyDao(Context context) {
        DBHelper helper = new DBHelper(context);
        mDb = helper.getWritableDatabase();
    }
    //获取数据库的对象
    public SQLiteDatabase getDb(){
        if (mDb!=null){
            return mDb;
        }
        return null;
    }
    //用来执行一个sql语句
    public void execSql(String sql,String[] args){
        if (args!=null){
            mDb.execSQL(sql,args);
        }else {
            mDb.execSQL(sql);
        }
    }
}
