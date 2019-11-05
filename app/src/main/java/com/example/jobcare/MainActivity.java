package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences loginCheck;
    private String id;
    private boolean isBusiness;
    private Button attention;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginCheck = getSharedPreferences("loginSetting",MODE_PRIVATE);
        load();


        attention = findViewById(R.id.notification);

        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AttentaionActivity.class);
                startActivity(intent);
            }
        });
    }

    // LOGIN PAGE MOVE
    public void  LoginPage(View view){
        if (id==null) { // 로그인 페이지로 이동
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }else{

            if (isBusiness){ // 사업자 페이지
                Intent intent = new Intent(this, MypageBusinessActivity.class);
                startActivity(intent);

            }else {// 일반 사용자 페이지
                Intent intent = new Intent(this, MyPageMemberActivity.class);
                startActivity(intent);
            }
        }
    }

    // 위치로 찾기
    public void jobFindLocation(View view){
        Intent intent = new Intent(this, JobFindLocationActivity.class);
        startActivity(intent);

    }

    // 직업 별로 찾기
    public void jobFindSelect (View view){
        Intent intent = new Intent(this, JobFindSelectActivity.class);
        startActivity(intent);
    }

    //훈련정보
    public void jobEducation(View view){
        Intent intent = new Intent(this,JobEducationActivity.class);
        startActivity(intent);
    }
    private void load(){
        id = loginCheck.getString("member_id",null);
        isBusiness = loginCheck.getBoolean("member_isBusiness", false);
    }
}
