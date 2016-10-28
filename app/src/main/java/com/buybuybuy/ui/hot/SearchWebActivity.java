package com.buybuybuy.ui.hot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.buybuybuy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchWebActivity extends AppCompatActivity {

    @BindView(R.id.wv_search)
    WebView wvSearch;
    @BindView(R.id.activity_search_web)
    RelativeLayout activitySearchWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_web);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        WebSettings settings = wvSearch.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        String url = intent.getStringExtra("url");
        //是否支持缩放
        settings.setSupportZoom(true);

        wvSearch.loadUrl(url);
        wvSearch.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });



    }
}
