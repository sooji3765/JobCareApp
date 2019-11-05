package com.example.jobcare;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jobcare.dto.Member;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

public class MypageBusinessActivity extends AppCompatActivity {

    private SharedPreferences user;
    private TextView name;
    private MemberCheck memberCheck;
    private String id;
    private ImageView imageView, btnImage;
    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_business);

        memberCheck = new MemberCheck();
        user = getSharedPreferences("loginSetting",MODE_PRIVATE);
        id = user.getString("member_id",null);
        Member member = memberCheck.getMember(id);
        name = findViewById(R.id.name);
        name.setText(member.getBusinessName());

        imageView = findViewById(R.id.image);
        btnImage = findViewById(R.id.imgBtn);

        tedPermission();


        byte[] img = member.getImg();
        if (img!=null){
            Bitmap bitmap = byteArrayToBitmap(img);
            imageView.setImageBitmap(bitmap);
        }

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAlbum();
            }
        });

    }
    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    public void logout(View view){
        SharedPreferences.Editor editor = user.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(getApplicationContext(),"로그아웃",Toast.LENGTH_LONG);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    // 공고 관리
    public void manageNotice(View view) {
        Intent intent = new Intent(this, NoticeActivity.class);
        startActivity(intent);
    }

    //지원자 목록 보기
    public void applyShow(View view) {
        Intent intent = new Intent(this,ApplyListActivity.class);
        intent.putExtra("businessId",id);
        startActivity(intent);
    }

    private void tedPermission() {
        PermissionListener permissionListener = new PermissionListener(){
            @Override
            public void onPermissionGranted() {
                // 권한 요청
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                // 실패
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {

            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if(tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e("취소 : ", tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();

            Cursor cursor = null;

            try {
                String[] proj = { MediaStore.Images.Media.DATA };

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));
                Log.d("GET FILE NAME :",tempFile.toString()+"");

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();
        }
    }

    private void setImage() {

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        originalBm.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        memberCheck.imgAdd(byteArray,id);

        imageView.setImageBitmap(originalBm);
    }

    public Bitmap byteArrayToBitmap( byte[] byteArray ) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( byteArray, 0, byteArray.length ) ;
        return bitmap ;
    }
}
