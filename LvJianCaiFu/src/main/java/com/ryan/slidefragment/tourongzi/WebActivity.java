package com.ryan.slidefragment.tourongzi;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ryan.slidefragment.generaldemo.R;

public class WebActivity extends Activity {

    private WebView web;
    private String url;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web=(WebView)findViewById(R.id.web);
        Bundle mBundle = new Bundle();
        mBundle = this.getIntent().getExtras();
        url = (mBundle.getSerializable("wang")).toString();
        System.out.println("url="+url);
        web.loadUrl("http://" + url);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return false;
            }
        });
        WebSettings websetting = web.getSettings();
        websetting.setJavaScriptEnabled(true);
         //缩放页面方式2. 会出现放大缩小的按钮
        websetting.setSupportZoom(true);
        websetting.setBuiltInZoomControls(true);


        web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


    }




}

