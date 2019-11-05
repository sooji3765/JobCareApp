package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Notice;

import java.util.ArrayList;

public class LikeNoticeActivity extends AppCompatActivity {

    ArrayList<Notice> list;
    private ListView listView;
    private LikeHandler likeHandler;
    private SharedPreferences sharedPreferences;

    private NoticeAdapter2 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_notice);
        listView = findViewById(R.id.noticeList);
        likeHandler = new LikeHandler();
        sharedPreferences = getSharedPreferences("loginSetting",MODE_PRIVATE);
        final String email = sharedPreferences.getString("member_id",null);

        list = likeHandler.allLike(email);
        adapter = new NoticeAdapter2(this, list, email);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),JobNoticeActivity.class);
                intent.putExtra("id_cp",list.get(position).getId_cp()+"");
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void back_btn(View view) {
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
