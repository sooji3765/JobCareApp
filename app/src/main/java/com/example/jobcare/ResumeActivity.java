package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Resume;

import java.util.ArrayList;

public class ResumeActivity extends AppCompatActivity {

    ArrayList<Resume> resumes;
    private ListView listView;
    private ResumeHandler resumeHandler = new ResumeHandler();
    private SharedPreferences user;
    private Button writeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        user = getSharedPreferences("loginSetting",MODE_PRIVATE);

        String id = user.getString("member_id",null);
        resumes = resumeHandler.getResume(id);

        final ApplyAdapter adapter = new ApplyAdapter(this, resumes);
        writeBtn = findViewById(R.id.writeResume);
        listView = findViewById(R.id.resumelist);
        listView.setAdapter(adapter);

        if (resumes.size()>0) {
            adapter.notifyDataSetChanged();
            writeBtn.setEnabled(false);
            writeBtn.setText("이력서 작성 완료");
        }else{
            adapter.notifyDataSetInvalidated();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ResumeDetailActivity.class);
                intent.putExtra("idx",resumes.get(position).getId());
                startActivity(intent);
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteResumeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void back_btn(View view) {
        Intent intent = new Intent(this,MyPageMemberActivity.class);
        startActivity(intent);
        finish();
    }
}
