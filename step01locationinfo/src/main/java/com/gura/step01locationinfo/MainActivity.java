package com.gura.step01locationinfo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener,
            LocationListener{

    GoogleApiClient gClient;
    EditText console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //정보를 출력할 EditText 객체의 참조값
        console=(EditText)findViewById(R.id.console);

        if(gClient==null){
            //GoogleApiClient 객체의 참조값 얻어내서 맴버필드에 저장
            gClient=new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }
    //버튼을 눌렀을때 호출되는 메소드
    public void moveToMap(View v){
        Intent intent=new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //GoogleApiClient 연결 요청
        gClient.connect();
    }

    @Override
    protected void onStop() {
        //GoogleApiClient 연결 해제
        gClient.disconnect();
        super.onStop();
    }
    //GoogleApiClient 에 연결 되었을때 호출되는 메소드
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        console.append("GoogleApiClient Connected! \r\n");


        //위치 정보를 얻어오기 위한 초기화 작업을 한다.
        if(Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //여기가 수행되면 사용자가 직접 퍼미션 스위치를 키도록 요청이 된것이다.
            return; //메소드 종료
        }
        //가장 최근의 위치 정보 얻어오기
        Location loc=LocationServices
                .FusedLocationApi.getLastLocation(gClient);
        if(loc != null){
            //위도 경도 얻어오기
            double lat=loc.getLatitude();
            double lon=loc.getLongitude();
            //정보 출력하기
            String info="위도:"+lat+" 경도:"+lon+"\r\n";
            console.append(info);
        }
        //변하는 위치 정보를 계속 얻어오기 위한 작업
        //위치정보 요청객체
        LocationRequest locRequest=new LocationRequest();
        locRequest.setInterval(10000); // 기본 10초 주기
        locRequest.setFastestInterval(5000); // 빠른주기는 5초 주기
        locRequest.setSmallestDisplacement(5);// 5 m 이상 위치정보가 변했을때
        //정확도가 높은 방법을 우선 순위로 한다.
        locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //위치정보가 바뀌었을때 정보를 받을 리스너 등록
        LocationServices.FusedLocationApi
                .requestLocationUpdates(gClient, locRequest, this);

    }// onConnected()

    //GoogleApiClient 에 연결이 지연되었을때 호출되는 메소드
    @Override
    public void onConnectionSuspended(int i) {
        console.append("connection Suspended! \r\n");
    }
    //GoogleApiClient 에 연결이 실패 했을때 호출되는 메소드
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        console.append("connection Failed! \r\n");
    }
    //위치 정보가 바뀌었을때 호출되는 메소드
    @Override
    public void onLocationChanged(Location location) {
        console.append("onLocationChanged ! \r\n");
        //위도 경도 얻어오기
        double lat=location.getLatitude();
        double lon=location.getLongitude();
        //정보 출력하기
        String info="위도:"+lat+" 경도:"+lon+"\r\n";
        console.append(info);
    }
}









