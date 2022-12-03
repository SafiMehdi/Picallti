package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import adapters.OffresAdapter;
import adapters.VehiculeTypesAdapter;
import data.Offre;
import data.VehiculeType;

public class OffrePageActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewCat;
    BottomBarFragment frag = new BottomBarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre_page);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();
        ButterKnife.bind(this);

        ImageView img = (ImageView) findViewById(R.id.imageView6);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(OffrePageActivity.this, WhatYouNeedActivity.class));
            }
        });

        recyclerView = findViewById(R.id.view_holder_offers);
        recyclerViewCat = findViewById(R.id.view_holder_vehicule_type);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewCat.setLayoutManager(linearLayoutManager2);

        ArrayList<VehiculeType> cat = new ArrayList<>();
        cat.add(new VehiculeType("Bike", "bicycle"));
        cat.add(new VehiculeType("Electric Bike", "electric_bike"));
        cat.add(new VehiculeType("Scooter", "kick_scooter"));
        cat.add(new VehiculeType("Motorcycle", "motorcycle"));

        adapter2 = new VehiculeTypesAdapter(cat);
        recyclerViewCat.setAdapter(adapter2);


        ArrayList<Offre> offres =new ArrayList<>();
        offres.add(new Offre("Pikala VTT Lekher", "Katmchi finma bghiti gha rkeb u zid asahbi wayli", 12, "bicycle"));
        offres.add(new Offre("Motor lahuma barik", "Swinga jaya mn asfi chi haja lahuma barik akhay diali", 50, "motorcycle"));
        offres.add(new Offre("Boukchlita lhrba", "Hadi bla mandwi eliha , sl3a kadwi ela rasha asahbi", 10, "bicycle"));
        offres.add(new Offre("Motor makaynch fhalu juj", "Had lmotor dor so9 kaml la l9iti bhalu aji dfl elia", 60, "motorcycle"));

        adapter=new OffresAdapter(offres);
        recyclerView.setAdapter(adapter);
    }
}