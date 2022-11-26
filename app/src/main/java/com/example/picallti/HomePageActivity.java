package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;

import DataBase.PicalltiDbHelper;
import data.Commentaire;
import data.Favoris;
import data.Note;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;

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
        try {


            VehiculeType vehiculeType = new VehiculeType("typeV");
            db.vehiculeTypeDbHelper.insertVehiculeType(vehiculeType);
            Vehicule vehicule = new Vehicule("nomV","marque","description",vehiculeType);
            db.vehiculeDbHelper.insertVehicule(vehicule);
            Offre offre = new Offre(34,"title","decriiiiiiption","localisation",67,"time","vente",user,vehicule, LocalDateTime.now());
            db.offreDbHelper.insertOffre(offre);
            Commentaire commentaire = new Commentaire("commentaire text",user,offre,LocalDateTime.now());
            db.commentaireDbHelper.insertCommentaire(commentaire);
            Favoris favoris = new Favoris(user,offre,LocalDateTime.now());
            db.favorisDbHelper.insertFavoris(favoris);
            Note note = new Note(4,user,offre,LocalDateTime.now());
            db.noteDbHelper.insertNote(note);


        } catch (ParseException e) {
            e.printStackTrace();
        }

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