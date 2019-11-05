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
import com.example.jobcare.dto.Resume;

import java.util.ArrayList;

public class ApplyAdapter2 extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Resume> sample;
    MemberCheck memberCheck = new MemberCheck();

    public ApplyAdapter2(Context context, ArrayList<Resume> data) {
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
    public Resume getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.applier_item2,null);

        TextView nameView = (TextView)view.findViewById(R.id.name);
        TextView titleView = (TextView)view.findViewById(R.id.title);
        String id  = sample.get(position).getId();
        Member member = memberCheck.getMember(id);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        byte[] img = member.getImg();
        if (img!=null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);

            imageView.setImageBitmap(bitmap);
        }
        nameView.setText(sample.get(position).getName());
        titleView.setText(sample.get(position).getTitle());
        return view;
    }


}
