package com.gura.wear03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // LinearLayout 으로 구성되어 있는 xml 문서로 화면구성도
        // 가능하다
        setContentView(R.layout.rect_activity_main);
        // TextView 의 참조값 얻어와서 맴버필드에 저장하기
        mTextView=(TextView)findViewById(R.id.text);

        mTextView.setText("김구라");
    }
    //버튼을 눌렀을때 호출되는 메소드
    public void btnClicked(View v){
        Toast.makeText(this, "clicked!", Toast.LENGTH_SHORT).show();
    }
}









