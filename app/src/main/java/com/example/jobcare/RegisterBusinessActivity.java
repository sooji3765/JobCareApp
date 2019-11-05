package com.example.jobcare;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Member;

public class RegisterBusinessActivity extends AppCompatActivity {
    private EditText id;
    private EditText password;
    private EditText password_ch;
    private EditText businessName;
    private EditText businessAddress;
    private EditText businessNumber;
    private MemberCheck memberCheck = new MemberCheck();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);
        id = findViewById(R.id.idRegister);
        password = findViewById(R.id.passResister);
        password_ch = findViewById(R.id.pass_register_check);
        businessName = findViewById(R.id.businessName);
        businessAddress = findViewById(R.id.businessAddress);
        businessNumber = findViewById(R.id.businessNumber);
    }

    // 이전으로
    public void back_btn(View view) {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }



    public void registerBusiness(View view) {
        String business_id = id.getText().toString();
        String business_pw = password.getText().toString();
        String business_pw_ch = password_ch.getText().toString();
        String business_name = businessName.getText().toString();
        String business_address = businessAddress.getText().toString();
        String business_number = businessNumber.getText().toString();

        boolean isValid = false;

        if (business_pw.equals(business_pw_ch)){
            Toast.makeText(getApplicationContext(),"비밀번호를 확인해주세요",Toast.LENGTH_LONG);
            password_ch.setFocusable(true);
        }

        if (business_id!=null&&business_pw!=null&&business_name!=null&&business_address!=null&&business_number!=null)
            isValid = true;

        Member member = new Member();
        member.setId(business_id);
        member.setPassword(business_pw);
        member.setBusinessName(business_name);
        member.setBusinessAddress(business_address);
        member.setBusinessNumber(business_number);
        member.setBusiness(true); // 일반 회원

        boolean isRegister = memberCheck.memberRegister(member);

        Log.d("IS RESITER RESULT :", isRegister+" ");

        if (isValid&&isRegister) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
