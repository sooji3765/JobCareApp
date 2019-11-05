package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Member;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private MemberCheck memberCheck = new MemberCheck();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.idEdit);
        password = findViewById(R.id.passEdit);
    }

    public void HomeBtn (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Login(View view){

        boolean isValid = memberCheck.memberValid(email.getText().toString(), password.getText().toString());

        if (isValid) { // 아이디와 비밀번호가 존재하면 Main으로

            SharedPreferences sharedPreferences = getSharedPreferences("loginSetting",MODE_PRIVATE);
            Member member = memberCheck.getMember(email.getText().toString());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("member_id",member.getId());
            editor.putString("member_name",member.getName());
            editor.putString("member_age",String.valueOf(member.getAge()));
            editor.putBoolean("member_isBusiness",member.isBusiness());
            editor.commit();
            Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{ // 없으면 다시 입력
            Toast.makeText(getApplicationContext(),"아이디와 비밀번호를 확인해주세요",Toast.LENGTH_LONG).show();
        }
    }

    public void Register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
