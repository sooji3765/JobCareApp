package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Member;
import com.example.jobcare.dto.Resume;

public class ResumeDetailActivity extends AppCompatActivity {

    private TextView id;
    private TextView age;
    private TextView name;
    private TextView phone;
    private TextView job;
    private TextView cando;
    private TextView title;
    private TextView introduce;
    private Button modify;
    private ResumeHandler resumeHandler = new ResumeHandler();
    private MemberCheck memberCheck;
    private SharedPreferences sharedPreferences;
    private String ids;
    private ImageView imageView;
    private boolean isBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_detail);
        memberCheck = new MemberCheck();
        sharedPreferences = getSharedPreferences("loginSetting",MODE_PRIVATE);
        load();

        Intent intent = getIntent();

        String idx = intent.getStringExtra("idx");

        Member member = memberCheck.getMember(idx);
        Resume resume = resumeHandler.getResume(idx).get(0);

        id = findViewById(R.id.detail_email);
        age = findViewById(R.id.detail_age);
        name = findViewById(R.id.detail_name);
        phone = findViewById(R.id.detail_phone);
        job = findViewById(R.id.detail_jobs);
        cando = findViewById(R.id.detail_cando);
        title = findViewById(R.id.detail_title);
        introduce = findViewById(R.id.detail_intro);

        imageView = findViewById(R.id.image);

        id.setText(resume.getId());
        age.setText(resume.getAge()+"");
        name.setText(resume.getId());
        phone.setText(resume.getPhone());
        job.setText(resume.getJobs());
        cando.setText(resume.getCanjobs());
        title.setText(resume.getTitle());
        introduce.setText(resume.getIntroduce());

        if (member.getImg()!=null){
            Bitmap bitmap= byteArrayToBitmap(member.getImg());
            imageView.setImageBitmap(bitmap);
        }
        //if (isBusiness) modify.setVisibility(View.GONE);
    }

    public void back_btn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void load(){
        ids= sharedPreferences.getString("member_id",null);
        isBusiness = sharedPreferences.getBoolean("member_isBusiness", false);
    }
    public Bitmap byteArrayToBitmap(byte[] byteArray ) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( byteArray, 0, byteArray.length ) ;
        return bitmap ;
    }
}
