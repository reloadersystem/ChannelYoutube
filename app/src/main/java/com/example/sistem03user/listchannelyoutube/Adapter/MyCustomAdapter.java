package com.example.sistem03user.listchannelyoutube.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sistem03user.listchannelyoutube.MainActivity;
import com.example.sistem03user.listchannelyoutube.Model.VideoDetails;
import com.example.sistem03user.listchannelyoutube.R;
import com.example.sistem03user.listchannelyoutube.VideoPlayActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter

{
    Activity activity;
    ArrayList<VideoDetails> videoDetailsArrayList;

    LayoutInflater inflater;



    public MyCustomAdapter(MainActivity activity, ArrayList<VideoDetails> videoDetailsArrayList) {
        this.activity=activity;
        this.videoDetailsArrayList = videoDetailsArrayList;

    }

    @Override
    public int getCount() {
        return this.videoDetailsArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return videoDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup viewGroup)
    {

        if(inflater==null)
        {
            inflater= this.activity.getLayoutInflater();
        }

        if(convertView==null)
        {
            convertView= inflater.inflate(R.layout.custom_item,null);
        }

        ImageView imageView= convertView.findViewById(R.id.imageView);
        TextView textView= convertView.findViewById(R.id.mytitle);
        LinearLayout linearLayout= (LinearLayout)convertView.findViewById(R.id.root);
        final VideoDetails videoDetails= (VideoDetails) this.videoDetailsArrayList.get(position);



        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(activity, VideoPlayActivity.class);
                intent.putExtra("videoId", videoDetails.getVideoId());

                activity.startActivity(intent);



            }
        });



        Picasso.get().load(videoDetails.getUrl()).into(imageView);

        textView.setText(videoDetails.getTitle());


        return convertView;
    }
}
