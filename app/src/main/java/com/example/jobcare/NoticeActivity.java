package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Notice;
import com.example.jobcare.dto.Resume;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<Notice> notices;
    private NoticeHandler noticeHandler = new NoticeHandler();
    private SharedPreferences user;
    NoticeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        listView = findViewById(R.id.noticelist);

        user = getSharedPreferences("loginSetting",MODE_PRIVATE);

        String id = user.getString("member_id",null);
        notices = noticeHandler.getNotice(id);

        adapter = new NoticeAdapter(this, notices);
        listView.setAdapter(adapter);

        if (notices.size()>0) {
            adapter.notifyDataSetChanged();

        }else {
            adapter.notifyDataSetInvalidated();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(NoticeActivity.this,"id_cp="+notices.get(position).getId_cp(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),NoticeDetailActivity.class);
                intent.putExtra("id_cp",notices.get(position).getId_cp()+"");
               startActivity(intent);
            }
        });

    }

    public void writeNotice(View view) { // 공고 쓰기

        Intent intent = new Intent(this,WriteNoticeActivity.class);
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

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
