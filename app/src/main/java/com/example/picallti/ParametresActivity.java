package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ParametresActivity extends AppCompatActivity {

    BottomBarFragment frag = new BottomBarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

    }
}