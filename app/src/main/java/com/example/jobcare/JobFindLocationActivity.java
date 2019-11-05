package com.example.jobcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;


public class JobFindLocationActivity extends AppCompatActivity {

    private Button selectBtn;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;
    private CheckBox checkBox8;
    private CheckBox checkBox9;
    private CheckBox checkBox10;
    private CheckBox checkBox11;
    private CheckBox checkBox12;
    private CheckBox checkBox13;
    private CheckBox checkBox14;
    private CheckBox checkBox15;
    private CheckBox checkBox16;
    private ArrayList<String> local;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_find_location);
        sharedPreferences =  getSharedPreferences("loginSetting",MODE_PRIVATE);
        Intent intent = getIntent();
        final String jobs = intent.getStringExtra("jobs");

        local = new ArrayList<>();

        selectBtn = findViewById(R.id.select_btn);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (local.size()==0){
                    Toast.makeText(getApplicationContext(),"지역을 선택하세요",Toast.LENGTH_LONG).show();
                }else if(local.size()>3){
                    Toast.makeText(getApplicationContext(),"3개 이하로 선택하세요",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),JobSearchListActivity.class );
                    intent.putStringArrayListExtra("location",local);
                    if (jobs!=null)
                        intent.putExtra("jobs",jobs);
                    startActivity(intent);
                    finish();
                }
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


    public void onCheckboxClicked(View view) {

        boolean checked  = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.seoul :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                     local.remove(((CheckBox) view).getText().toString());
                }
            break;
            case R.id.gyeonggi :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.incheon :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.busan :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.daegu :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.gwangju :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.daejeon :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.ulsan :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.gangwon :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.gyeongsangnam :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.gyeongbuk :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.jeonnam :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.jeonbuk :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.chungcheongnam :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.Chungcheongbook :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;
            case R.id.jeju :
                if (checked) local.add(((CheckBox) view).getText().toString());
                else{
                    if (local.contains(((CheckBox) view).getText().toString()))
                        local.remove(((CheckBox) view).getText().toString());
                }
                break;

        }
    }


}
