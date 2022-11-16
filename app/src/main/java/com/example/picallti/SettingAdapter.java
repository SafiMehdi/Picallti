package com.example.picallti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SettingAdapter extends ArrayAdapter<Setting> {
    private ArrayList<Setting> settings;

    public SettingAdapter(Context context, int resource, ArrayList<Setting> settings){
        super(context, resource, settings);
        this.settings =settings;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_layout, parent, false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.transImage);
        imageView.setBackgroundResource(settings.get(position).getImageID());

        TextView viewTitle = (TextView) convertView.findViewById(R.id.transaction);
        viewTitle.setText(settings.get(position).getSettingName());


        return convertView;
    }
}
