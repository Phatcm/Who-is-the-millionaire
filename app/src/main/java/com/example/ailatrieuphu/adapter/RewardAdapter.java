package com.example.ailatrieuphu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

public class RewardAdapter extends ArrayAdapter<String> {
    Context mct;
    ArrayList<String> arr;
    int posQuest = 1;

    public RewardAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.mct = context;
        this.arr = new ArrayList<>(objects);

    }
    public void setPosQuest(int posQuest) {
        this.posQuest = posQuest;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_reward, null);
        }
        if(arr.size()>0){
            int pos = 15 - position;
            TextView txvReward = convertView.findViewById(R.id.txtReward);
            if(pos%5 == 0){
                txvReward.setTextColor(Color.parseColor("#FFFFFF"));
            }else{
                txvReward.setTextColor(Color.parseColor("#FF9800"));
            }
            String space;
            if(pos/10 >0){
                space = "     ";
            }else{
                space = "       ";
            }
            String textShow = pos+space+"$"+arr.get(position);
            txvReward.setText(textShow);

            if(pos == posQuest) {
                txvReward.setBackgroundColor(Color.parseColor("#FFC107"));
                txvReward.setTextColor(Color.parseColor("#FF9800"));
            }else{
                txvReward.setBackgroundColor(Color.parseColor("#00FFC107"));
            }
        }
        return convertView;
    }
}
