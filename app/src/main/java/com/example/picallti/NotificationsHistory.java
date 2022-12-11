package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import adapters.NotificationAdapter;
import data.Notification;
import data.User;

public class NotificationsHistory extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    BottomBarFragment frag = new BottomBarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_history);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();

        recyclerView = findViewById(R.id.view_holder_notification);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        User user = new User("nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");

        ArrayList<Notification> notifications =new ArrayList<>();
        notifications.add(new Notification("Bicycle VTT", "Mehdi Safi commented your post",R.drawable.bicycle,LocalTime.now(), LocalDate.now(),user));
       // notifications.add(new Notification("Motor lahuma barik", "Swinga jaya mn asfi chi haja lahuma barik akhay diali", Date.valueOf("2015-03-31"), "motorcycle"));
        //notifications.add(new Notification("Boukchlita lhrba", "Hadi bla mandwi eliha , sl3a kadwi ela rasha asahbi", Date.valueOf("2015-03-31"), "bicycle"));
        //notifications.add(new Notification("Motor makaynch fhalu juj", "Had lmotor dor so9 kaml la l9iti bhalu aji dfl elia", Date.valueOf("2015-03-31"), "motorcycle"));

        adapter=new NotificationAdapter(notifications);
        recyclerView.setAdapter(adapter);
    }
}