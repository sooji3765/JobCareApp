package com.example.jobcare;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void HomeBtn (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // 일반 회원 가입
    public void MemberRegister(View view) {
        Intent intent = new Intent(this, RegisterMemberActivity.class);
        startActivity(intent);
        finish();
    }

    // 사업자 회원 가입
    public void BusinessRegister(View view) {
        Intent intent = new Intent(this, RegisterBusinessActivity.class);
        startActivity(intent);
        finish();
    }
}
