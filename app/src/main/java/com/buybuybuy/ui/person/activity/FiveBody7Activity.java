package com.buybuybuy.ui.person.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.buybuybuy.R;

public class FiveBody7Activity extends AppCompatActivity {
    private String url = "http://h5.m.taobao.com/trip/flight/search/index.html?ttid=12ali0000071&ali_trackid=2:mm_43590817_6508898_22828999:1476114722_211_806251710&ttid=2014_umyapp_23356547%40baichuan_android_2.1.0&e=onnuPM05fNhw4vFB6t2Z2iperVdZeJviEViQ0P1Vf2kguMN8XjClAjyJFIsvn6qOR36tO_xKsbog0U3r_3LDoyJHJeSD9FeEX8CJ_yhNRVpRQnj-xjnzyH7uq6sLYIquJj4SsIKGsaSbkzBfwit0j3VZl7I1Eki4hlqGmiJlpBomoXd7JzVWAX_eNZiGA9qsjm_oWDzDPosglw5xCqvUx8fz2Mggak0SomfkDJRs-hU&type=2";
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_five_body7);
        wv = (WebView)findViewById(R.id.wvByTicket);
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
