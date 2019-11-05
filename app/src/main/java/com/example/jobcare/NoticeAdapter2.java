package com.example.jobcare;

import android.content.Context;
import android.content.SharedPreferences;
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

public class NoticeAdapter2 extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Notice> sample;
    LikeHandler handler = new LikeHandler();
    String email = null;
    MemberCheck memberCheck = new MemberCheck();
    public NoticeAdapter2(Context context, ArrayList<Notice> data, String e) {
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
        final View views = mLayoutInflater.inflate(R.layout.notice_items2,null);

        TextView nameView = (TextView)views.findViewById(R.id.n_name);
        TextView titleView = (TextView)views.findViewById(R.id.n_title);
        TextView payView = (TextView)views.findViewById(R.id.n_wage);
        ImageButton deleteItem = (ImageButton)views.findViewById(R.id.deleteItem);
        ImageView imageView = (ImageView)views.findViewById(R.id.image);
        //즐겨찾기에서 지우기

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("email delete===>",email);
                handler.deleteLike(sample.get(position).getId_cp(),email);
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
