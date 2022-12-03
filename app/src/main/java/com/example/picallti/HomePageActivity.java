package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class HomePageActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences myPrefs = getSharedPreferences(PREFS_NAME, 0);
                boolean hasLoggedIn = myPrefs.getBoolean("isCheckedPref", false);
                if (hasLoggedIn) {
                    Intent intent = new Intent(HomePageActivity.this, OffrePageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(HomePageActivity.this, login_page.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}