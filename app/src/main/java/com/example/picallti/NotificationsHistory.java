package com.example.picallti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

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
    //The function that implements the sidebar
    public void Sidebar(){
        NavigationView navView = findViewById(R.id.sidebar_view);
        navView.inflateMenu(R.menu.sidebar_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent_profile = new Intent(NotificationsHistory.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(NotificationsHistory.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(NotificationsHistory.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(NotificationsHistory.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(NotificationsHistory.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    case R.id.nav_logout:
                        Toast.makeText( NotificationsHistory.this, "You've been disconnected!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NotificationsHistory.this, login_page.class));
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_notification_page);

        ImageButton toggleButton = findViewById(R.id.sidebar_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open or close the navigation drawer when the button is clicked
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

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

        //Sidebar implementation
        Sidebar();
    }
}