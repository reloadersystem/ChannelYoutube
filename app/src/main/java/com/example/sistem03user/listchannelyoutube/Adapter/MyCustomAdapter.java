package com.example.sistem03user.listchannelyoutube.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.sistem03user.listchannelyoutube.MainActivity;
import com.example.sistem03user.listchannelyoutube.Model.VideoDetails;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter

{
    Activity activity;
    ArrayList<VideoDetails> videoDetailsArrayList;

    public MyCustomAdapter(MainActivity activity, ArrayList<VideoDetails> videoDetailsArrayList) {
        this.activity=activity;
        this.videoDetailsArrayList = videoDetailsArrayList;

    }

    @Override
    public int getCount() {
        return videoDetailsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        return convertView;
    }
}
