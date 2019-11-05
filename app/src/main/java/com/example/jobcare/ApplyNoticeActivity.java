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

public class ApplyNoticeActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Notice> list;
    private ApplyHandler applyHandler;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_notice);
        listView = findViewById(R.id.applyList);

        sharedPreferences = getSharedPreferences("loginSetting",MODE_PRIVATE);
        final String email = sharedPreferences.getString("member_id",null);

        applyHandler = new ApplyHandler();
        list = applyHandler.getNotice(email);

        NoticeAdapter4 adapter = new NoticeAdapter4(this,list,email);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), JobNoticeActivity.class);
                intent.putExtra("id_cp", list.get(position).getId_cp()+"");
                startActivity(intent);
            }
        });


    }

    public void back_btn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
