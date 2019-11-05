package com.example.jobcare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Member;
import com.example.jobcare.dto.Notice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class WriteNoticeActivity extends AppCompatActivity {

    private TextView businessName;
    private TextView businessNumber;
    private TextView businessAddress;
    private EditText title;
    private EditText needCnt;
    private Button lastDay;
    private CheckBox job1,job2,job3,job4,job5;
    private EditText pay;
    private EditText workPeriod;
    private EditText workDay;
    private EditText workTime;
    private Spinner workLocation;
    private CheckBox do1,do2,do3,do4,do5,do6,do7,do8;
    private EditText detailWork;
    private NoticeHandler noticeHandler = new NoticeHandler();
    private MemberCheck memberCheck = new MemberCheck();
    private SharedPreferences user;
    private String email;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> location;
    private String selectLocal;
    int y=0, m=0, d=0;
    private Button makeBtn;
    private String[] resumeTitle ={
            "손이 빠르고 성실한 직원을 구합니다.",
            "교대근무 가능하고 오래 일하실 분 구합니다.",
            "가족처럼 함께 즐겁게 일 할 직원분들을 모십니다.",
            "단순 분류 경험자 우대합니다.",
            "책임감 있게 일하실 분들을 모집합니다."
    };


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notice);

        businessName = findViewById(R.id.business_name);
        businessNumber = findViewById(R.id.business_number);
        businessAddress = findViewById(R.id.business_address);
        title = findViewById(R.id.notice_title);
        lastDay = findViewById(R.id.notice_last);
        needCnt = findViewById(R.id.notice_cnt);
        workDay = findViewById(R.id.notice_day);
        workTime = findViewById(R.id.notice_time);
        pay = findViewById(R.id.notice_pay);
        workPeriod = findViewById(R.id.notice_period);
        workLocation = findViewById(R.id.notice_location);
        detailWork = findViewById(R.id.notice_detail);
        job1 = findViewById(R.id.notice_job1);
        job2 = findViewById(R.id.notice_job2);
        job3 = findViewById(R.id.notice_job3);
        job4 = findViewById(R.id.notice_job4);
        job5 = findViewById(R.id.notice_job5);

        do1 = findViewById(R.id.notice_do1);
        do2 = findViewById(R.id.notice_do2);
        do3 = findViewById(R.id.notice_do3);
        do4 = findViewById(R.id.notice_do4);
        do5 = findViewById(R.id.notice_do5);
        do6 = findViewById(R.id.notice_do6);
        do7 = findViewById(R.id.notice_do7);
        do8 = findViewById(R.id.notice_do8);

        makeBtn = findViewById(R.id.makeBtn);

        location = new ArrayList<>();
        location.add("서울");
        location.add("경기");
        location.add("인천");
        location.add("부산");
        location.add("대구");
        location.add("광주");
        location.add("대전");
        location.add("울산");
        location.add("강원");
        location.add("경남");
        location.add("경북");
        location.add("전남");
        location.add("전북");
        location.add("충남");
        location.add("충북");
        location.add("제주");

        lastDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, location);
        workLocation.setAdapter(arrayAdapter);

        workLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectLocal = location.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //이력서 제목 자동완성 버튼
        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int ran = random.nextInt(3);
                title.setText(resumeTitle[ran]);

            }
        });

        user =getSharedPreferences("loginSetting",MODE_PRIVATE);
        String idx = user.getString("member_id",null);

        Member member = memberCheck.getMember(idx);
        email = member.getId();
        Log.d("MEMBER GET",member.toString());

        businessName.setText(member.getBusinessName());
        businessAddress.setText(member.getBusinessAddress());
        businessNumber.setText(member.getBusinessNumber());
    }

    public void write(View view){
        Notice notice = new Notice();
        int idx = noticeHandler.getNextKey();

        ArrayList<String> jobs = new ArrayList<>();
        ArrayList<String> candos = new ArrayList<>();

        notice.setId_cp(idx);
        notice.setBusinessName(businessName.getText().toString());
        notice.setBusinessAddress(businessAddress.getText().toString());
        notice.setBusinessNumber(businessNumber.getText().toString());
        notice.setTitle(title.getText().toString());
        notice.setNeedCnt(needCnt.getText().toString());
        notice.setId(email);
        notice.setLastDate(lastDay.getText().toString());
        notice.setPay(pay.getText().toString());
        notice.setWorkDay(workDay.getText().toString());
        notice.setWorkTime(workTime.getText().toString());
        notice.setWorkDetail(detailWork.getText().toString());
        notice.setWorkPeriod(workPeriod.getText().toString());
        notice.setWorkLocation(selectLocal);

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

        if (do1.isChecked())
            candos.add(do1.getText().toString());
        if (do2.isChecked())
            candos.add(do2.getText().toString());
        if (do3.isChecked())
            candos.add(do3.getText().toString());
        if (do4.isChecked())
            candos.add(do4.getText().toString());
        if (do5.isChecked())
            candos.add(do5.getText().toString());
        if (do6.isChecked())
            candos.add(do6.getText().toString());
        if (do7.isChecked())
            candos.add(do7.getText().toString());
        if (do8.isChecked())
            candos.add(do8.getText().toString());

        notice.setJob(jobs.toString());
        notice.setNeedDo(candos.toString());

        noticeHandler.setNotice(notice);

        Intent intent = new Intent(this,NoticeActivity.class);
        startActivity(intent);
        finish();
    }

    public void back_btn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month+1;
                d = dayOfMonth;
                String date = String.format("%d-%d-%d",y,m,d);
                lastDay.setText(date);
            }
        },2019, 1, 11);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }

}
