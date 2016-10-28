package com.buybuybuy;

import android.app.Application;

import com.buybuybuy.http.OkHttpClientUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


public class MyApplication extends Application {

    private OkHttpClientUtils okHttpClientUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttpClient();//调用初始化OkHttpClient对象方法

        //初始化
        UMShareAPI.get(this);
        //AppID,AppSecret
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    //
    private void initOkHttpClient() {
        okHttpClientUtils = OkHttpClientUtils.getOkHttpClientUtils(this);
    }
}
