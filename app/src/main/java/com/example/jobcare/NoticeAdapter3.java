package com.example.jobcare;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jobcare.dto.Member;
import com.example.jobcare.dto.Notice;

import java.util.ArrayList;

public class NoticeAdapter3 extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Notice> sample;
    MemberCheck memberCheck = new MemberCheck();

    public NoticeAdapter3(Context context, ArrayList<Notice> data) {
        super();
        mContext = context;
        sample =data;
        mLayoutInflater = LayoutInflater.from(mContext);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View views = mLayoutInflater.inflate(R.layout.notice_items,null);

        TextView nameView = (TextView)views.findViewById(R.id.n_name);
        TextView titleView = (TextView)views.findViewById(R.id.n_title);
        TextView payView = (TextView)views.findViewById(R.id.n_wage);
        ImageView imageView = (ImageView)views.findViewById(R.id.image);

        String id  = sample.get(position).getId();
        Member member = memberCheck.getMember(id);


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
