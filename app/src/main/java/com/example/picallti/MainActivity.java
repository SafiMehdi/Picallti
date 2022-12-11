package com.example.picallti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    BottomBarFragment frag = new BottomBarFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //BottomBarFragment frag = new BottomBarFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();
        setContentView(R.layout.activity_home_page);
    }

}