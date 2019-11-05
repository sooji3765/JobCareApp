package com.example.jobcare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jobcare.dto.Resume;

import java.util.ArrayList;

public class ResumeAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Resume> sample;
    private ResumeHandler handler = new ResumeHandler();

    public ResumeAdapter(Context context, ArrayList<Resume> data) {
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
        View view = mLayoutInflater.inflate(R.layout.resume_item,null);

        TextView titleView = view.findViewById(R.id.title);
        titleView.setText(sample.get(position).getTitle());
        ImageButton deleteBtn = view.findViewById(R.id.deleteItem);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("id tag===",sample.get(position).getId());
                handler.deleteItem(sample.get(position).getId());
                sample.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
