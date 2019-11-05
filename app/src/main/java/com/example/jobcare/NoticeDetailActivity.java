package com.example.jobcare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Member;
import com.example.jobcare.dto.Notice;

public class NoticeDetailActivity extends AppCompatActivity {

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
    private ImageView imageView;
    private MemberCheck memberCheck;
    private NoticeHandler handler = new NoticeHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        Intent intent = getIntent();
        String idx = intent.getStringExtra("id_cp");
        Notice notice = handler.getItem(Integer.parseInt(idx));
        memberCheck = new MemberCheck();

        Member member = memberCheck.getMember(notice.getId());

        byte[] img = member.getImg();

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
        imageView = findViewById(R.id.image);

        if (img!=null){
            Bitmap bitmap= byteArrayToBitmap(img);
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



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void back_btn(View view) {
        onBackPressed();
    }

    public Bitmap byteArrayToBitmap(byte[] byteArray ) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( byteArray, 0, byteArray.length ) ;
        return bitmap ;
    }
}
