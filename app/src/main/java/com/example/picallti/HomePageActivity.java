package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageActivity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        VideoView videoView =(VideoView)findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.logo_reveal);

        MediaController mediaController =new MediaController(this);
        //link mediaController to videoView  
        mediaController.setAnchorView(videoView);
        //allow mediaController to control our videoView  
        videoView.setMediaController(mediaController);
        videoView.start();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}