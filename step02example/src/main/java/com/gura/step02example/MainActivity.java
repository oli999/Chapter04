package com.gura.step02example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //필요한 맴버필드 정의하기
    EditText inputMsg;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputMsg=(EditText)findViewById(R.id.inputMsg);
        webView=(WebView)findViewById(R.id.webView);

        // WebView 에 필요한 셋팅 하기
        WebSettings ws=webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setAllowUniversalAccessFromFileURLs(true);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //WebView 클라이언트 객체 넣어주기
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        // assets 폴더에 있는 index.html 페이지를 WebView 에 로딩 시키기
        String url="file:///android_asset/www/index.html";
        webView.loadUrl(url);

        //WebView 에 NativeBride 객체를 연결한다.
        webView.addJavascriptInterface(new NativeBride(), "NB");
    }
    //WebView 로 보내기 버튼을 눌렀을때 호출되는 메소드
    public void send(View v){
        //입력한 문자열을 읽어온다.
        String msg=inputMsg.getText().toString();
        webView.loadUrl("javascript:toWebView('"+msg+"')");
    }
    //WebView 의 javascript 런타임과 소통하기 위한 클래스 정의하기
    class NativeBride{

        // javascript 런타임에서 호출할 메소드
        @JavascriptInterface
        public void fromWebView(final String msg){
            //호출하면 실행순서가 여기로 들어오는데 UI 스레드가 아니다.

            //UI 스레드에서 실행될수 있도록 Runnable 객체를 넣어준다.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //여기는 UI 스레드 이다.
                    Toast.makeText(MainActivity.this, msg,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}







