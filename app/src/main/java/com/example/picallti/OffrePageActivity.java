package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import adapters.OffresAdapter;
import adapters.VehiculeTypesAdapter;
import butterknife.ButterKnife;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;
import retrofit.OffreApi;
import retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container, frag).commit();
        ButterKnife.bind(this);

        ImageView img = (ImageView) findViewById(R.id.filter);
        img.setOnClickListener(new View.OnClickListener() {
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
        //cat.add(new VehiculeType("Bike"));
        //cat.add(new VehiculeType("Electric Bike"));
        //cat.add(new VehiculeType("Scooter"));
        //cat.add(new VehiculeType("Motorcycle"));

        adapter2 = new VehiculeTypesAdapter(cat);
        recyclerViewCat.setAdapter(adapter2);





        /*VehiculeType vehiculeType = new VehiculeType("typeV");
       // db.vehiculeTypeDbHelper.insertVehiculeType(vehiculeType);
        Vehicule vehicule = new Vehicule("marque",vehiculeType);
        User user = new User("nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");
        offres.add(  new Offre(R.drawable.motorcycle,"Motorcycle","A perfectly working Motorcycle, available starting from now ","localisation",67, "LocalTime.now()","vente",user,vehicule,  LocalDate.of(2020, 1, 8)));
        offres.add(new Offre("Bicycle VTT", "Vente",12,"A perfectly working bicycle, available starting from now ",  "bicycle",user,vehicule));
        //offres.add(new Offre("Motor lahuma barik", "Vente",50,"Swinga jaya mn asfi chi haja lahuma barik akhay diali",  "motorcycle",user,vehicule));
        //offres.add(new Offre("Boukchlita lhrba", "Vente",10,"Hadi bla mandwi eliha , sl3a kadwi ela rasha asahbi",  "bicycle",user,vehicule));
        //offres.add(new Offre("Motor makaynch fhalu juj", "Vente",60,"Had lmotor dor so9 kaml la l9iti bhalu aji dfl elia", "motorcycle",user,vehicule));
*/
        RetrofitService retrofitService = new RetrofitService();
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);
        Call<List<Offre>> call = offreApi.getOffers();
        call.enqueue(new Callback<List<Offre>>() {
            @Override
            public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                assert response.body() != null;
                //System.out.println(response.body().toString() );
                System.out.println("working");
                ArrayList<Offre> offres =new ArrayList<>();
                offres = new ArrayList<Offre>(response.body());
                System.out.println(offres);
                adapter = new OffresAdapter(getApplicationContext(), offres);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Offre>> call, Throwable t) {
                System.out.println("exceeeption");
                Logger.getLogger(SingUp.class.getName()).log(Level.SEVERE, "Error Occured", t);

            }
        });


    }


}