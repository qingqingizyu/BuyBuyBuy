package com.buybuybuy.ui.person.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.buybuybuy.R;

public class FiveBody6Activity extends AppCompatActivity {
    private String url = "http://s.click.taobao.com/t?e=m%3D2%26s%3DS6vBfFPw5ygcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAjyJFIsvn6qOeVL0KyhNWcgg0U3r%2F3LDoyJHJeSD9FeEX8CJ%2FyhNRVpRQnj%2BxjnzyH7uq6sLYIqu0Q7QOybCaQy9AmARIwX9K5xNLLIwPQT2naYpFBIfC%2F0YZ8x9F3uZq7SIJ5A7Lxi1wtdwuLODCp2rGrg9Mk16sESLxLu3ikz8hE5XI3nVfHQ5WG5tSVnamqJn5AyUbPoV";
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_five_body6);
        wv = (WebView)findViewById(R.id.wvAliTravel);
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
