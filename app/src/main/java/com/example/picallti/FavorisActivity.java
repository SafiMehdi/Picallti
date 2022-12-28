package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import adapters.FavoritesAdapter;
import adapters.NotificationAdapter;
import data.Favoris;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;

public class FavorisActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    BottomBarFragment frag = new BottomBarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();

        recyclerView = findViewById(R.id.view_holder_favorites);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        VehiculeType vehiculeType = new VehiculeType("typeV");

        Vehicule vehicule = new Vehicule("marque",vehiculeType);
        User user = new User("nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");

        ArrayList<Offre> offres =new ArrayList<>();
        offres.add(  new Offre(R.drawable.motorcycle,"Motorcycle","A perfectly working Motorcycle, available starting from now ","localisation",67, "LocalTime.now()","vente",user,vehicule, " LocalDate.of(2020, 1, 8)","Kenitra"));
        offres.add(new Offre("Bicycle VTT", "Vente",12,"A perfectly working bicycle, available starting from now ",  "bicycle",user,vehicule,"kenitra"));
        //offres.add(new data.Offre("Motor lahuma barik", "Swinga jaya mn asfi chi haja lahuma barik akhay diali", 50, "motorcycle"));
        //offres.add(new data.Offre("Boukchlita lhrba", "Hadi bla mandwi eliha , sl3a kadwi ela rasha asahbi", 10, "bicycle"));
        //offres.add(new Offre("Motor makaynch fhalu juj", "Had lmotor dor so9 kaml la l9iti bhalu aji dfl elia", 60, "motorcycle"));

        ArrayList<Favoris> favoris =new ArrayList<>();
        favoris.add(new Favoris(offres.get(0)));
        favoris.add(new Favoris(offres.get(1)));
        //favoris.add(new Favoris(offres.get(2)));
        //favoris.add(new Favoris(offres.get(3)));

        adapter=new FavoritesAdapter(favoris);
        recyclerView.setAdapter(adapter);
    }
}