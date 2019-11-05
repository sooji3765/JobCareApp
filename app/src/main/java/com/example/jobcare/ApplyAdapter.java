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
import com.example.jobcare.dto.Resume;

import java.util.ArrayList;

public class ApplyAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Resume> sample;
    MemberCheck memberCheck = new MemberCheck();
    private ResumeHandler handler = new ResumeHandler();
    ApplyHandler applyHandler = new ApplyHandler();

    public ApplyAdapter(Context context, ArrayList<Resume> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.applier_item,null);

        TextView nameView = (TextView)view.findViewById(R.id.name);
        TextView titleView = (TextView)view.findViewById(R.id.title);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        ImageButton deleteBtn = (ImageButton) view.findViewById(R.id.deleteItem);



        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("id tag===",sample.get(position).getId());
                if(applyHandler.isEmail(sample.get(position).getId())==null) {
                    handler.deleteItem(sample.get(position).getId());
                    sample.remove(position);
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(view.getContext(),"이 이력서로 지원한 곳이 있습니다.",Toast.LENGTH_LONG).show();
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
        nameView.setText(sample.get(position).getName());
        titleView.setText(sample.get(position).getTitle());
        return view;
    }


}
