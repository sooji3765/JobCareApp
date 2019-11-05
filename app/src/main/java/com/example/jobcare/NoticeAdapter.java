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

public class NoticeAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Notice> sample;
    NoticeHandler handler = new NoticeHandler();
    MemberCheck memberCheck = new MemberCheck();
    public NoticeAdapter(Context context, ArrayList<Notice> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View views = mLayoutInflater.inflate(R.layout.notice_items2,null);

        TextView nameView = (TextView)views.findViewById(R.id.n_name);
        TextView titleView = (TextView)views.findViewById(R.id.n_title);
        TextView payView = (TextView)views.findViewById(R.id.n_wage);
        ImageButton deleteItem = (ImageButton)views.findViewById(R.id.deleteItem);
        ImageView imageView = (ImageView)views.findViewById(R.id.image);

        //비지니스 회원이 공고 지우기

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler.deleteItem(sample.get(position).getId_cp())) {
                    sample.remove(position);
                    notifyDataSetChanged();
                }
                else{
                    Log.d("dont","지울수 없음");
                    Toast.makeText(views.getContext(),"이 공고로 지원한 지원자가 있습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });

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
