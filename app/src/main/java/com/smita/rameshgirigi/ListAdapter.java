package com.smita.rameshgirigi;

import android.content.Context;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private List<String> lists;

    public ListAdapter(List<String> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    public void notifyData(List<String> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_text.setText(lists.get(position));

        // convert date and time as per our requirement
        String time = lists.get(position);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale.US);
            String dateObj = String.valueOf(sdf.parse(time));
            holder.tv_timestamp.setText(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        holder.switchoff.setText(lists.get(position));
        if (holder.switchoff != null) {
            holder.switchoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String msg = isChecked ? "ON" : "OFF";
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_text, tv_timestamp;
        Switch switchoff;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_text =  itemView.findViewById(R.id.tv_text);
            tv_timestamp =  itemView.findViewById(R.id.tv_timestamp);
            switchoff =  itemView.findViewById(R.id.switchoff);
        }
    }
}