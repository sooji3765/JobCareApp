package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Member;
import com.example.jobcare.dto.Resume;

import java.util.ArrayList;
import java.util.Random;

public class WriteResumeActivity extends AppCompatActivity {

    private TextView id;
    private TextView age;
    private TextView phone;
    private TextView name;
    private EditText title;
    private CheckBox job1;
    private CheckBox job2;
    private CheckBox job3;
    private CheckBox job4;
    private CheckBox job5;
    private CheckBox cando1;
    private CheckBox cando2;
    private CheckBox cando3;
    private CheckBox cando4;
    private CheckBox cando5;
    private CheckBox cando6;
    private CheckBox cando7;
    private CheckBox cando8;
    private EditText introduce;
    private SharedPreferences user;
    private MemberCheck memberCheck = new MemberCheck();
    private ResumeHandler resumeHandler = new ResumeHandler();
    private Button titleBtn, introBtn, btnBack;

    String[] titleMake ={
            "센스 있고 적응력 좋은 인재입니다.",
            "몸도 마음도 건강한 인재입니다.",
            "믿음직하고 끈기 있는 인재입니다.",
            "습득력이 빠르고 잘 적응할 자신이 있습니다.",
            "언제나 성실한 자세로 일하겠습니다."
    };

    String[] introMake ={
            "누구보다 열심히 일하는 성실한 일꾼이 되겠습니다.",
            "빠른 손으로 10명의 몫을 해낼 자신 있습니다.",
            "항상 최선을 다하는 모습을 보여드리겠습니다.",
            "시키시는 일은 모두 잘 해낼 자신 있습니다.",
            "밝은 미소와 경쾌한 목소리로 즐겁게 일하겠습니다."
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_resume);

        id = findViewById(R.id.resume_id);
        age = findViewById(R.id.resume_age);
        phone = findViewById(R.id.resume_phone);
        title = findViewById(R.id.resume_title);
        name = findViewById(R.id.resume_name);

        job1 = findViewById(R.id.job1);
        job2 = findViewById(R.id.job2);
        job3 = findViewById(R.id.job3);
        job4 = findViewById(R.id.job4);
        job5 = findViewById(R.id.job5);

        cando1 = findViewById(R.id.option1);
        cando2 = findViewById(R.id.option2);
        cando3 = findViewById(R.id.option3);
        cando4 = findViewById(R.id.option4);
        cando5 = findViewById(R.id.option5);
        cando6 = findViewById(R.id.option6);
        cando7 = findViewById(R.id.option7);
        cando8 = findViewById(R.id.option8);
        introduce = findViewById(R.id.introduce);
        titleBtn = findViewById(R.id.titleMakeBtn);
        introBtn = findViewById(R.id.introBtn);
        btnBack = findViewById(R.id.btnBack);

        user =getSharedPreferences("loginSetting",MODE_PRIVATE);
        String idx = user.getString("member_id",null);

        Member member = memberCheck.getMember(idx);

        Log.d("MEMBER GET",member.toString());
        id.setText(member.getId());
        age.setText(member.getAge()+"");
        phone.setText(member.getPhone());
        name.setText(member.getName());


        titleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random= new Random();
                int ran = random.nextInt(5);
                Log.d("clicksss=========",ran+" "+titleMake[ran]);
                title.setText(titleMake[ran]);
            }
        });

        introBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int rs = r.nextInt(5);
                introduce.setText(introMake[rs]);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void write(View view) {

        String resume_id = id.getText().toString();
        String resume_age = age.getText().toString();
        String resume_phone = phone.getText().toString();
        String resume_title = title.getText().toString();
        final String resume_name = name.getText().toString();
        ArrayList<String> jobs = new ArrayList<>();
        ArrayList<String> candos = new ArrayList<>();
        String resume_introduce = introduce.getText().toString();

        if (job1.isChecked())
            jobs.add(job1.getText().toString());
        if (job2.isChecked())
            jobs.add(job2.getText().toString());
        if (job3.isChecked())
            jobs.add(job3.getText().toString());
        if (job4.isChecked())
            jobs.add(job4.getText().toString());
        if (job5.isChecked())
            jobs.add(job5.getText().toString());

        String resume_job= jobs.toString();

        if (cando1.isChecked())
            candos.add(cando1.getText().toString());
        if (cando2.isChecked())
            candos.add(cando2.getText().toString());
        if (cando3.isChecked())
            candos.add(cando3.getText().toString());
        if (cando4.isChecked())
            candos.add(cando4.getText().toString());
        if (cando5.isChecked())
            candos.add(cando5.getText().toString());
        if (cando6.isChecked())
            candos.add(cando6.getText().toString());
        if (cando7.isChecked())
            candos.add(cando7.getText().toString());
        if (cando8.isChecked())
            candos.add(cando8.getText().toString());

        String resume_do = candos.toString();

        Resume resume = new Resume();
        resume.setId(resume_id);
        resume.setAge(Integer.parseInt(resume_age));
        resume.setPhone(resume_phone);
        resume.setTitle(resume_title);
        resume.setJobs(resume_job);
        resume.setCanjobs(resume_do);
        resume.setIntroduce(resume_introduce);
        resume.setName(resume_name);

        Log.d("Resume Write====",resume.toString());



        resumeHandler.setResume(resume);

        Intent intent = new Intent(this,ResumeActivity.class);
        startActivity(intent);
        finish();
    }
}
