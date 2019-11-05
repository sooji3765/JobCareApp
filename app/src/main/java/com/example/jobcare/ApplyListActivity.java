package com.example.jobcare;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Resume;

import java.util.ArrayList;

public class ApplyListActivity extends AppCompatActivity {

    private ListView listViews;
    private ArrayList<Resume> items;
    private ApplyHandler applyHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_list);

        applyHandler = new ApplyHandler();
        final Intent intent = getIntent();
        String business = intent.getStringExtra("businessId");

        listViews = findViewById(R.id.applyList);
        items = applyHandler.getApplier(business);

        final ApplyAdapter2 adapter = new ApplyAdapter2(this, items);
        listViews.setAdapter(adapter);

        if (items.size()>0) {
            adapter.notifyDataSetChanged();

        }else {
            adapter.notifyDataSetInvalidated();
        }


        // 지원자 클릭시  결과 화면
        listViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ApplyListActivity.this,"adsf",Toast.LENGTH_LONG).show();
                Intent intents = new Intent(ApplyListActivity.this, ResumeDetailActivity.class);
                intents.putExtra("idx",items.get(position).getId());
                startActivity(intents);
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
}
