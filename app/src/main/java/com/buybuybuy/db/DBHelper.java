package com.buybuybuy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 创建数据库
 * Created by Administrator on 2016/10/14.
 */

public class DBHelper extends SQLiteOpenHelper {
    //数据库的名称
    private static final String DB_NAME = "bybyby.db";
    //数据库的版本号.版本号>=1
    private static final int DB_VERSION = 1;
    //创建数据库
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    //创建表的方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE bybyby(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "img CHAR(100)," +               //1、img : http://img3.tbcdn.cn/tfscom/i2/2085695885/TB2hlPTX_gX61BjSspmXXaFcFXa_!!2085695885.jpg_320x320q90.jpg
                "title CHAR(100)," +             //2、title : 遇见香芬香水洗发水沐浴露套装
                "price CHAR(100)," +             //3、price : 5.8
                "original_price CHAR(100)," +    //4、original_price : 48
                "buy_num CHAR(100)," +           //5、buy_num : 3403
                "source CHAR(100)," +            //6、source : taobao
                "url CHAR(100)";                 //7、url : http://s.click.taobao.com/t?e=m%3D2%26s%3DLLHDJc6sZVscQipKwQzePOeEDrYVVa64K7Vc7tFgwiFRAdhuF14FMfj2FyJapjMZt4hWD5k2kjOjzoOPBMY9nmisZ%2FXOnDwqdIMyWQqW33n7osd9P%2FFWmehSg1yj7h0zRhXF%2FamAi%2FAEaTy6sNbrVsYOae24fhW0
        db.execSQL(sql);
    }
    //升级数据库的方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.i("TAG", "数据库升级了...");
        }
    }
    //开启数据库
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
/**
 * private final ImageView ivImg;
 * private final TextView tvTitle;
 * private final TextView tvPrice;
 * private final TextView tvOriginalPrice;
 * private final TextView tvBuyNum;
 * private final TextView tvSource;
 */
