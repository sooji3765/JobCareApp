package com.example.jobcare;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.jobcare.dto.Member;
import com.example.jobcare.dto.Notice;

import java.util.ArrayList;

public class NoticeAdapter4 extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Notice> sample;
    ApplyHandler handler = new ApplyHandler();
    String email = null;
    MemberCheck memberCheck = new MemberCheck();
    public NoticeAdapter4(Context context, ArrayList<Notice> data, String e) {
        super();
        mContext = context;
        sample =data;
        mLayoutInflater = LayoutInflater.from(mContext);
        email = e;
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public Notice getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View views = mLayoutInflater.inflate(R.layout.notice_items3,null);

        TextView nameView = (TextView)views.findViewById(R.id.n_name);
        TextView titleView = (TextView)views.findViewById(R.id.n_title);
        TextView payView = (TextView)views.findViewById(R.id.n_wage);
        Button deleteItem = (Button)views.findViewById(R.id.deleteApply);
        ImageView imageView = (ImageView)views.findViewById(R.id.image);
        //지원 취소
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.deleteItem(sample.get(position).getId_cp(),email);
                sample.remove(position);
                notifyDataSetChanged();
            }
        });

        Member member = memberCheck.getMember(sample.get(position).getId());


        byte[] img = member.getImg();
        if (img!=null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);

            imageView.setImageBitmap(bitmap);
        }
        titleView.setText(sample.get(position).getTitle());
        nameView.setText(sample.get(position).getBusinessName());
        payView.setText(sample.get(position).getPay()+"만원");
        return views;
    }


}
