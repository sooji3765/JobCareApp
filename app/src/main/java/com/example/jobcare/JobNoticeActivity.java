package com.example.jobcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.*;

public class JobNoticeActivity extends AppCompatActivity {

    private TextView businessName;
    private TextView businessNumber;
    private TextView businessAddress;
    private TextView title;
    private TextView needCnt;
    private TextView lastDay;
    private TextView job;
    private TextView pay;
    private TextView workPeriod;
    private TextView workDay;
    private TextView workTime;
    private TextView workLocation;
    private TextView doWant;
    private TextView detailWork;
    private CheckBox like;

    private NoticeHandler handler = new NoticeHandler();
    private MemberCheck memberCheck;
    private SharedPreferences sharedPreferences;
    private LikeHandler likeHandler ;
    private ApplyHandler applyHandler;
    private Button applyBtn;
    private Notice notice;
    private ImageView imageView;
    private String email;
    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_notice);

        likeHandler = new LikeHandler();
        applyHandler = new ApplyHandler();
        memberCheck = new MemberCheck();

        Intent intent = getIntent();
        final String idx = intent.getStringExtra("id_cp");
        notice = handler.getItem(Integer.parseInt(idx));
        sharedPreferences = getSharedPreferences("loginSetting",MODE_PRIVATE);
        email = sharedPreferences.getString("member_id",null);

        if (email!=null) member = memberCheck.getMember(email);
        Apply apply = applyHandler.isApply(email,notice.getId_cp());

        Member business = memberCheck.getMember(notice.getId());
        businessName = findViewById(R.id.busin_name);
        businessAddress = findViewById(R.id.busin_address);
        businessNumber = findViewById(R.id.busin_num);
        title = findViewById(R.id.busin_title);
        needCnt = findViewById(R.id.busin_cnt);
        lastDay = findViewById(R.id.busin_last);
        job = findViewById(R.id.busin_job);
        pay = findViewById(R.id.busin_pay);
        workPeriod = findViewById(R.id.busin_period);
        workDay = findViewById(R.id.busin_day);
        workTime = findViewById(R.id.busin_time);
        workLocation = findViewById(R.id.busin_location);
        doWant = findViewById(R.id.busin_do);
        detailWork = findViewById(R.id.busin_detail);
        like = findViewById(R.id.likeBtn);
        applyBtn = findViewById(R.id.applyBtn);
        imageView = findViewById(R.id.image);

        if (member!=null) {
            if (member.isBusiness()) {
                like.setVisibility(View.GONE);
                applyBtn.setVisibility(View.GONE);
            }
        }else{
            like.setVisibility(View.GONE);
            applyBtn.setVisibility(View.GONE);
        }

        if (apply!=null){
            applyBtn.setText("지원완료");
            applyBtn.setEnabled(false);
        }

        if (business.getImg()!=null){
            Bitmap bitmap = byteArrayToBitmap(business.getImg());
            imageView.setImageBitmap(bitmap);
        }
        businessName.setText(notice.getBusinessName());
        businessNumber.setText(notice.getBusinessNumber());
        businessAddress.setText(notice.getBusinessAddress());
        title.setText(notice.getTitle());
        needCnt.setText(notice.getNeedCnt());
        lastDay.setText(notice.getLastDate());
        job.setText(notice.getJob());
        pay.setText(notice.getPay());
        workPeriod.setText(notice.getWorkPeriod());
        workDay.setText(notice.getWorkDay());
        workTime.setText(notice.getWorkTime());
        workLocation.setText(notice.getWorkLocation());
        doWant.setText(notice.getNeedDo());
        detailWork.setText(notice.getWorkDetail());

        like.setChecked(likeHandler.getLike(Integer.parseInt(idx),email));

        like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Log.d("press: ",isChecked+"");
                    int id = likeHandler.getNextKey();
                    Like like = new Like();
                    like.setId_cp(id);
                    like.setNotice_idx(Integer.parseInt(idx));
                    like.setMember_email(email);
                    likeHandler.addLike(like);
                }else{ // false 면 제거
                    likeHandler.deleteLike(Integer.parseInt(idx),email);
                }
            }
        });

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
            }
        });
    }

    public void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("지원 상황");
        builder.setMessage("지원하시겠습니까?");

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Resume resume = new ResumeHandler().oneResume(email);

                if (resume!=null) {
                    Apply item = new Apply();
                    int idx = applyHandler.getNextKey();
                    item.setIdx(idx);
                    item.setNotice_idx(notice.getId_cp());
                    item.setBusinessId(notice.getId());
                    item.setApplyId(email);
                    applyHandler.addApply(item);
                    Toast.makeText(JobNoticeActivity.this, "지원이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    applyBtn.setText("지원 완료");
                    applyBtn.setEnabled(false);
                }else{
                    Toast.makeText(JobNoticeActivity.this, "이력서를 작성해 주세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void back_btn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public Bitmap byteArrayToBitmap(byte[] byteArray ) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( byteArray, 0, byteArray.length ) ;
        return bitmap ;
    }
}
