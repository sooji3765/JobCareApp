package com.example.jobcare;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class JobEducationActivity extends AppCompatActivity {


    // 웹 뷰
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_education);
        mWebView = findViewById(R.id.webView);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.worktogether.or.kr/eduInfo/trainInfo/eduTrainInfoList.do");
        mWebView.setWebChromeClient(new WebChromeClient());
    }
}
