package com.example.jobcare;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class JobFindSelectActivity extends AppCompatActivity {

    private ArrayList<String> location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_find_select);
        Intent intent = getIntent();
        location = intent.getStringArrayListExtra("location");
    }

    public void myPage(View view) {
        Intent intent = new Intent(this,MyPageMemberActivity.class);
        startActivity(intent);
    }

    public void backBtn(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Job_Info_page(View view) {

        int btnId = view.getId();
        String jobName = null;

        switch (btnId){
            case R.id.jobBt1 :
                jobName = "생산/포장";
                break;
            case R.id.jobBt2 :
                jobName = "외식/서비스";
                break;
            case R.id.jobBt3 :
                jobName = "건설/기술";
                break;

            case R.id.jobBt4 :
                jobName = "청소/미화";
                break;
            case R.id.jobBt5 :
                jobName = "기타/보조";
                break;
        }

        Intent intent = new Intent(getApplicationContext(), JobSearchListActivity.class);
        intent.putExtra("jobs",jobName);
        if (location!=null)
            intent.putStringArrayListExtra("location",location);
        startActivity(intent);

    }
}
