package com.gura.step02webviewasset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView=(WebView)findViewById(R.id.webView);

        // WebView 에 필요한 셋팅 하기
        WebSettings ws=webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setAllowUniversalAccessFromFileURLs(true);

        //WebView 클라이언트 객체 넣어주기
        webView.setWebViewClient(new WebViewClient());

        // alert, confirm 등의 창을 띄울수 있도록
        webView.setWebChromeClient(new WebChromeClient());

        //로딩시킬 local file url 구성하기
        String url="file:///android_asset/www/index.html";
        //WebView 에 file url 에 있는 html 페이지 로딩 시키기
        webView.loadUrl(url);
    }
}










