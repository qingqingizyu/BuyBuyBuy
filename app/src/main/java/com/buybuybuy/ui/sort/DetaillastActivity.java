package com.buybuybuy.ui.sort;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.buybuybuy.R;

public class DetaillastActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaillast);
        webView = (WebView) findViewById(R.id.wb_detail);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebSettings settings = webView.getSettings();
        //支持脚本功能
        settings.setJavaScriptEnabled(true);
        //支持缩放
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
               if (webView!=null&&webView.canGoBack()){
                   webView.goBack();
               }else {
                   finish();
               }
        }
        return false;
    }
}
