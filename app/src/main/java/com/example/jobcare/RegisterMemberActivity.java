package com.example.jobcare;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Member;

public class RegisterMemberActivity extends AppCompatActivity {
    private MemberCheck memberCheck = new MemberCheck();
    private EditText id;
    private EditText password;
    private EditText passwordCheck;
    private EditText name;
    private EditText phone;
    private EditText age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_member);

        id = findViewById(R.id.member_id);
        password = findViewById(R.id.member_pass);
        passwordCheck = findViewById(R.id.member_passCheck);
        name = findViewById(R.id.member_name);
        age = findViewById(R.id.member_age);
        phone = findViewById(R.id.member_phone);

    }

    public void RegisterMember(View view) {

        String member_id = id.getText().toString();
        String member_pw = password.getText().toString();
        String member_pw_ch = passwordCheck.getText().toString();
        String member_name = name.getText().toString();
        String member_age = age.getText().toString();
        String member_phone = phone.getText().toString();

        //boolean isValid = false;

//        if (member_pw.equals(member_pw_ch)){
//            Toast.makeText(getApplicationContext(),"비밀번호를 확인해주세요",Toast.LENGTH_LONG);
//            passwordCheck.setFocusable(true);
//        }

        if (member_id.length()>0&&member_pw.length()>0&&member_name.length()>0&&member_age.length()>0&&member_phone.length()>0) {
            Member member = new Member();
            member.setId(member_id);
            member.setPassword(member_pw);
            member.setName(member_name);
            member.setAge(Integer.parseInt(member_age));
            member.setPhone(member_phone);
            member.setBusiness(false); // 일반 회원

            boolean isRegister = memberCheck.memberRegister(member);
            Log.d("IS RESITER RESULT :", isRegister+" ");


                Toast.makeText(getApplicationContext(),"로그인 하세요",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();

        }else{
            Toast.makeText(getApplicationContext(),"데이터를 입력해주세요",Toast.LENGTH_LONG).show();
        }

    }

    public void BackBtn(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
