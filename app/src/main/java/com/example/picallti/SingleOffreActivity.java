package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SingleOffreActivity extends AppCompatActivity {

    BottomBarFragment frag = new BottomBarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_offre);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();

    }
}