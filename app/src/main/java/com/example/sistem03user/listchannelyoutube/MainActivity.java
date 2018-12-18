package com.example.sistem03user.listchannelyoutube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistem03user.listchannelyoutube.Adapter.MyCustomAdapter;
import com.example.sistem03user.listchannelyoutube.Model.VideoDetails;
import com.google.android.youtube.player.YouTubePlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    // url  de  practica https://www.youtube.com/watch?v=BG9bm4TCy64

    ListView listView;

    String API_KEY="AIzaSyBBoMapePV_AthrQPLWevncKB-RVw6QXtw";

    ArrayList<VideoDetails> videoDetailsArrayList;
    MyCustomAdapter myCustomAdapter;
    String url="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PL6dvGWNWC1Uga4podWHHnSZQDyBFOsdhM&key=AIzaSyCI1oCTXwZzgVv7LDQ8NykSIUEWt247KnU&maxResults=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById(R.id.listView);
        videoDetailsArrayList= new ArrayList<>();

        myCustomAdapter= new MyCustomAdapter(MainActivity.this, videoDetailsArrayList);

        displayVideos();



    }

    private void displayVideos()
    {

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject= new JSONObject(response);
                    JSONArray jsonArray= jsonObject.getJSONArray("items");


                    for(int i=0;i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        JSONObject jsonVideoId= jsonObject.getJSONObject("id");
                        JSONObject jsonObjectSnippet= jsonObject1.getJSONObject("snippet");

                        JSONObject jsonObjectDefault=  jsonObjectSnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        String video_id = jsonVideoId.getString("videoId");
                        VideoDetails vd= new VideoDetails();

                        vd.setVideoId(video_id);
                        vd.setTitle(jsonObjectSnippet.getString("title"));
                        vd.setDescription(jsonObjectSnippet.getString("description"));
                        vd.setUrl(jsonObjectDefault.getString("url"));


                        videoDetailsArrayList.add(vd);

                    }


                    listView.setAdapter(myCustomAdapter);
                    myCustomAdapter.notifyDataSetChanged();
                    

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }

        );

        requestQueue.add(stringRequest);


    }
}
