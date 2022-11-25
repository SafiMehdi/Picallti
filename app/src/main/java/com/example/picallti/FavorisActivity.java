package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;

import adapters.FavoritesAdapter;
import adapters.NotificationAdapter;
import data.Favoris;
import data.Offre;

public class FavorisActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        recyclerView = findViewById(R.id.view_holder_favorites);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<Offre> offres =new ArrayList<>();
        offres.add(new data.Offre("Pikala VTT Lekher", "Katmchi finma bghiti gha rkeb u zid asahbi wayli", 12, "bicycle"));
        offres.add(new data.Offre("Motor lahuma barik", "Swinga jaya mn asfi chi haja lahuma barik akhay diali", 50, "motorcycle"));
        offres.add(new data.Offre("Boukchlita lhrba", "Hadi bla mandwi eliha , sl3a kadwi ela rasha asahbi", 10, "bicycle"));
        offres.add(new Offre("Motor makaynch fhalu juj", "Had lmotor dor so9 kaml la l9iti bhalu aji dfl elia", 60, "motorcycle"));


        ArrayList<Favoris> favoris =new ArrayList<>();
        favoris.add(new Favoris(offres.get(0)));
        favoris.add(new Favoris(offres.get(1)));
        favoris.add(new Favoris(offres.get(2)));
        favoris.add(new Favoris(offres.get(3)));

        adapter=new FavoritesAdapter(favoris);
        recyclerView.setAdapter(adapter);
    }
}