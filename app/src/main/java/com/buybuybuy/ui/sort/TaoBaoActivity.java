package com.buybuybuy.ui.sort;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.buybuybuy.R;

public class TaoBaoActivity extends Activity {

    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_bao);
        Intent intent = getIntent();
        final String path = intent.getStringExtra("path");
        wb = (WebView) findViewById(R.id.wb);
        WebSettings settings = wb.getSettings();
        //设置支持javascript脚本
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(true);
        //支持缩放
        settings.setSupportZoom(true);
        wb.loadUrl(path);
        //使网页变为app网页
        wb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        //设置按钮返回事件
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (wb!=null&&wb.canGoBack()){
                    wb.goBack();
                }else {
                    finish();
                }
        }
        return false;
    }
}
