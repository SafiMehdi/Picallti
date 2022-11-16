package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.net.DatagramSocket;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Settings extends AppCompatActivity {
    @BindView(R.id.listSettings)
    ListView listSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        ArrayList<Setting> settings = new ArrayList<Setting>();
        Integer[] imageID = {R.drawable.nom, R.drawable.notification, R.drawable.language, R.drawable.lock, R.drawable.support, R.drawable.help, R.drawable.log_out};
        String[] settingName = {getString(R.string.accountSettings), getString(R.string.notificationSettings),getString(R.string.languageSetting),getString(R.string.security),getString(R.string.help), getString(R.string.about), getString(R.string.logOut) };


        for( int i = 0; i<settingName.length; i++){
            Setting tr = new Setting( imageID[i], settingName[i] );
            settings.add(tr);
        }
        SettingAdapter adapter = new SettingAdapter(getApplicationContext(), R.layout.list_layout, settings);

        listSettings.setAdapter(adapter);
        listSettings.setClickable(true);
    }
}