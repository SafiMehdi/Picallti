package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import DataBase.PicalltiDbHelper;
import data.Commentaire;
import data.Favoris;
import data.Note;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;

public class HomePageActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        PicalltiDbHelper db = new PicalltiDbHelper(getApplicationContext());
        User user = new User("nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");
        try {
            db.userDbHelper.insertUser(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {


            VehiculeType vehiculeType = new VehiculeType("typeV");
            db.vehiculeTypeDbHelper.insertVehiculeType(vehiculeType);
            Vehicule vehicule = new Vehicule("marque",vehiculeType);
            db.vehiculeDbHelper.insertVehicule(vehicule);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            Offre offre = new Offre(34,"title46","decriiiiiiption","localisation",67, LocalTime.now(),"vente",db.userDbHelper.readUsers().get(46),vehicule,  LocalDate.of(2020, 1, 8));
            db.offreDbHelper.insertOffre(offre);
            Commentaire commentaire = new Commentaire("commentaire text",db.userDbHelper.readUsers().get(46),offre,LocalDate.now(),LocalTime.now());
            db.commentaireDbHelper.insertCommentaire(commentaire);
            Favoris favoris = new Favoris(db.userDbHelper.readUsers().get(46),offre);
            db.favorisDbHelper.insertFavoris(favoris);
            Note note = new Note(4,4,db.userDbHelper.readUsers().get(46),offre);
            db.noteDbHelper.insertNote(note);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            for (int i=0;i<db.offreDbHelper.readOffres().size();i++ ){
                //System.out.println(db.offreDbHelper.readOffres().size());
            }
           // System.out.println(db.userDbHelper.selectUserById(1).getNom());
            //System.out.println(db.vehiculeTypeDbHelper.selectVehiculeTypeById(1).getNom());
            //System.out.println(db.vehiculeDbHelper.selectVehiculeById(1).getNom());
            //System.out.println(db.offreDbHelper.selectOfferById(1).getTitre());
            //System.out.println(db.commentaireDbHelper.selectCommentById(1).getCommentaire());
            System.out.println("PPPPPPPPPPPPPPPPPPPPPP");
            //System.out.println(db.favorisDbHelper.readFavoris().get(1).getUser().getNom());
            //System.out.println(db.offreDbHelper.selectOfferById(1).getUser().getNom());
            System.out.println(db.userDbHelper.readUsers().size());
            System.out.println("PPPPPPPPPPPPPPPPPPPPPp");
            System.out.println(db.userDbHelper.selectUserById(46).getEmail());
            System.out.println("PPPPPPPPPPPPPPPPPPPPPp");
            System.out.println(db.offreDbHelper.selectOfferById(51).getUser().getNom());
            System.out.println(db.favorisDbHelper.selectFavorisById(52).getUser().getNom());
            db.close();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences myPrefs = getSharedPreferences(PREFS_NAME, 0);
                boolean hasLoggedIn = myPrefs.getBoolean("isCheckedPref", false);
                if (hasLoggedIn) {
                    Intent intent = new Intent(HomePageActivity.this, OffrePageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(HomePageActivity.this, login_page.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}