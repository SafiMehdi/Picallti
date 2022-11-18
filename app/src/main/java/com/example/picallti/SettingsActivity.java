package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapters.SettingAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import data.Setting;

public class SettingsActivity extends AppCompatActivity {
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

        ImageView back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, OffrePageActivity.class));
            }
        });

    }


    @OnItemClick(R.id.listSettings)
    public void onListSettingsItemClicked(int position){
        if (position == 0 ){
            Intent intent  = new Intent(SettingsActivity.this, ParametresCompte.class);
            startActivity(intent);

        }else if (position == 1){
            Intent intent  = new Intent(SettingsActivity.this, NotificationsHistory.class);
            startActivity(intent);
        }
        else if (position == 6){
            Toast.makeText( SettingsActivity.this, "You have been disconnected!",Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(SettingsActivity.this, login_page.class);
            startActivity(intent);
        }
    }
}