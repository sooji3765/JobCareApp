package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Notice;

import java.util.ArrayList;

public class JobSearchListActivity extends AppCompatActivity {

    private ArrayList<Notice> list;
    private ListView listView;
    private NoticeHandler noticeHandler = new NoticeHandler();
    private SharedPreferences sharedPreferences;
    private String id;
    private boolean isBusiness ;
    private Button lBtn;
    private Button jBtn;
    private ArrayList<String> locations;
    private String jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_list);
        lBtn = findViewById(R.id.locationBtn);
        jBtn = findViewById(R.id.jobBtn);
        listView = findViewById(R.id.listjob);

        final Intent intent = getIntent();
        locations = intent.getStringArrayListExtra("location");
        jobs = intent.getStringExtra("jobs");
        sharedPreferences =  getSharedPreferences("loginSetting",MODE_PRIVATE);


        if(jobs!=null) {
            jBtn.setText(jobs);
            if (locations!=null){
                   lBtn.setText(locations.toString());
                   String[] locationIn = locations.toArray(new String[locations.size()]);
                   list = noticeHandler.getAll(locationIn,jobs);
            }else {
                list = noticeHandler.getAll(jobs, 1);
            }
        }else{
            if (locations.size()>0) {
                lBtn.setText(locations.toString());
                String[] locationIn = locations.toArray(new String[locations.size()]);
                list =  noticeHandler.getAll(locationIn);
            }else{
                list = noticeHandler.getAll();
            }
        }



        load();


        final NoticeAdapter3 adapter3 = new NoticeAdapter3(this, list);

        listView.setAdapter(adapter3);

        if (list.size()>0){
            adapter3.notifyDataSetChanged();
        }else{
            adapter3.notifyDataSetInvalidated();
        }

        lBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), JobFindLocationActivity.class);
                intent1.putExtra("jobs",jobs);
                startActivity(intent1);
                finish();
            }
        });


        jBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), JobFindSelectActivity.class);
                intent1.putStringArrayListExtra("location",locations);
                startActivity(intent1);
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), JobNoticeActivity.class);
                intent.putExtra("id_cp",list.get(position).getId_cp()+"");
                startActivity(intent);
            }
        });
    }

    private void load(){
        id = sharedPreferences.getString("member_id",null);
        isBusiness = sharedPreferences.getBoolean("member_isBusiness", false);
    }

    public void myPage(View view) {

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

    public void homBtn(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
