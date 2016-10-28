package com.buybuybuy.ui.person.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.buybuybuy.R;

public class FiveBody8Activity extends AppCompatActivity {
//    private String url = "https://www.baidu.com/link?url=U9qecxkUrCQRxieUprFOjqJPOfAYotwR0u-KMCzI-5YKp6D4j-jdPAYkLa9Oosa5&wd=&eqid=d4c44dab0000f9dc0000000357fcd828";
    private String url = "https://tyh.taobao.com/?ali_trackid=2:mm_26632360_8858797_29866178:1476202362_2k3_1460016987&clk1=6ccfda1c88bd2560bf91eca2944e6ae6&spm=a231o.7076277.19985674832.1.MAwyOr&pvid=200_10.103.34.81_306_1476202157733#a=&c=0&q=&s=0&page=1&";
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_five_body8);
        wv = (WebView)findViewById(R.id.wvSpecialOffer);
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
