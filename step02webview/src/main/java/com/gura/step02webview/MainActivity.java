package com.gura.step02webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //필요한 맴버필드 정의하기
    WebView webView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.inputUrl);
        // WebView 의 참조값 얻어오기
        webView=(WebView)findViewById(R.id.webView);

        //WebSettings 객체를 얻어와서
        WebSettings ws=webView.getSettings();
        //javascript 해석이 가능하도록 한다.
        ws.setJavaScriptEnabled(true);

        //WebViewClient 객체 넣어주기
        webView.setWebViewClient(new WebViewClient());

        //WebView 에 컨텐츠 로딩 시키기
        webView.loadUrl("http://study.kimgura.net");
    }
    //이동 버튼을 눌렀을때 호출되는 메소드
    public void move(View v){
        //입력한 url 주소를 읽어온다.
        String url=editText.getText().toString();
        //WebView 에 로딩 시키기
        webView.loadUrl(url);
    }
}











