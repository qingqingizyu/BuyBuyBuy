package com.buybuybuy.ui.person.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.buybuybuy.R;

//TODO：购物车
public class FiveBody1Activity extends AppCompatActivity {
    private String url = "http://h5.m.taobao.com/awp/base/cart.htm?";
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_five_body1);
        wv = (WebView)findViewById(R.id.wvBody1);
        assert wv != null;
        //用来对WebView进行设置
        WebSettings settings = wv.getSettings();
        //支持javascript脚本功能
        settings.setJavaScriptEnabled(true);
        //是否支持缩放
        settings.setSupportZoom(true);
        //是否显示缩放控制器
        settings.setDisplayZoomControls(true);
        //加载url地址
        wv.loadUrl(url);

        //修改默认的浏览器
        wv.setWebViewClient(new WebViewClient(){
            //修改默认浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
