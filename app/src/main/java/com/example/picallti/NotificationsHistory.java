package com.example.picallti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import adapters.NotificationAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.Notification;

public class NotificationsHistory extends AppCompatActivity {

    BottomBarFragment frag = new BottomBarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();
        setContentView(R.layout.activity_notifications_history);
        ButterKnife.bind(this);
        ListView listView = (ListView)findViewById(R.id.notification_list_view);
        ArrayList<Notification> notifications  = new ArrayList<Notification>();
        notifications.add(new Notification("titre","androidx.appcompat.widget.AppCompatImageButton{120f22 VFED..C.. ......ID 0,0-0,0 #7f0800de app:id/homee}",R.drawable.notificationicon,new Date(5,5,5)));
        notifications.add(new Notification("titre","test",R.drawable.notificationicon,new Date(5,5,5)));
        notifications.add(new Notification("titre","test",R.drawable.notificationicon,new Date(5,5,5)));
        notifications.add(new Notification("titre","test",R.drawable.notificationicon,new Date(5,5,5)));
        NotificationAdapter notificationAdapter = new NotificationAdapter(getBaseContext(),R.layout.notification_list_element,notifications);
        listView.setAdapter(notificationAdapter);
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height =(int) (getResources().getDisplayMetrics().heightPixels/1.7) ;
        listView.setLayoutParams(params);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button boutton = findViewById(R.id.refresh_button);

    }

    @OnClick(R.id.refresh_button)
    public void refreshActivity(){
        System.out.println("heeeeere");
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        //this.recreate();
    }

}