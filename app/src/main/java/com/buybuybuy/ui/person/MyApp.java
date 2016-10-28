package com.buybuybuy.ui.person;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * 类描述:
 * 创建人:刘磊
 * 创建时间:16/10/13 10:00
 * 备注:
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        UMShareAPI.get(this);
        //AppID,AppSecret
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //新浪微博的回调地址
        //Config.REDIRECT_URL = "www.baidu.com";
    }

}
