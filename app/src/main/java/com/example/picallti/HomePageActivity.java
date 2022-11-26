package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;

import DataBase.PicalltiDbHelper;
import data.Offre;
import data.User;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        PicalltiDbHelper db = new PicalltiDbHelper(getApplicationContext());
        User user = new User("nom","prenom","M","test@test.com",78,"pass",78,"admin");
        try {
            db.userDbHelper.insertUser(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*Offre offre = new Offre(34,"title","decriiiiiiption","localisation",67,"time","vente",user);
        try {
            db.offreDbHelper.insertOffre(offre);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        Button seeOffersBtn = (Button) findViewById(R.id.button);
        seeOffersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(),login_page.class);
                startActivity(loginIntent);
            }
        });

    }
}