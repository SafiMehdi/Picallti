package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    BottomBarFragment frag = new BottomBarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();
        ImageView img = (ImageView) findViewById(R.id.profile_picture);
        img.setBackgroundResource(R.drawable.background_profile_picutre);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.addOfferButton)
    public void addClick(){
        Intent intent = new Intent(this,AjouterAnnonceActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.settingsBtn)
    public void settingsClick(){
        Intent intent = new Intent(this,ParametresActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.editProfileBtn)
    public void editClick(){
        Intent intent = new Intent(this,ModifierProfileActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.OffresBtn)
    public void OffresClick(){
        Intent intent = new Intent(this,PersonnalOfferActivity.class);
        startActivity(intent);
    }


}