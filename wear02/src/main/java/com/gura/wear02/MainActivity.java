package com.gura.wear02;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // res/layout/activity_main.xml 문서를 전개해서 화면 구성하기
        setContentView(R.layout.activity_main);
        // 전개한 문서에서 WatchViewStub 객체의 참조값 얻어오기
        final WatchViewStub stub = (WatchViewStub)
                        findViewById(R.id.watch_view_stub);
        // 기기가 사각형인지 원형인지 감지해서 화면구성이 되었을때
        // 호출되는 리스너 등록 하기
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            //실행순서가 여기에 들어오면 화면구성이 된것이다.
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                // TextView 의 참조값 얻어와서 참조값을 맴버필드에 저장하기
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
    }
}
