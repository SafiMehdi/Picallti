package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import com.google.gson.Gson;

import java.io.IOException;
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
import okhttp3.ResponseBody;
import retrofit.ImageDataApi;
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
    private TextView title;
    BottomBarFragment frag = new BottomBarFragment();

    //The function that implements the sidebar
    public void Sidebar(){
        NavigationView navView = findViewById(R.id.sidebar_view);
        navView.inflateMenu(R.menu.sidebar_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent_profile = new Intent(OffrePageActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(OffrePageActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(OffrePageActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(OffrePageActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(OffrePageActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_offre_page);

        ImageButton toggleButton = findViewById(R.id.sidebar_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open or close the navigation drawer when the button is clicked
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre_page);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container, frag).commit();

        //function to retrieve connected user
        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);

        //setting title name
        title = findViewById(R.id.textView7);
        if(connectedUser != null) {
            String Nom = connectedUser.getNom();
            title.setText("Hi "+Nom);

            String email = connectedUser.getEmail();
        }
        ImageView pp = (ImageView) findViewById(R.id.imageView2);
        RetrofitService retrofitService = new RetrofitService();
        ImageDataApi imageDataApi = retrofitService.getRetrofit().create(ImageDataApi.class);
        imageDataApi.downloadImage("test.jpg").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    byte[] r = new byte[0];
                    try {
                        r = response.body().bytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(r, 0, r.length);
                        pp.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        ImageView img = (ImageView) findViewById(R.id.filter);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(OffrePageActivity.this, WhatYouNeedActivity.class));
            }
        });

        recyclerView = findViewById(R.id.view_holder_offers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

       //RetrofitService retrofitService = new RetrofitService();
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

        //Sidebar implementation
        Sidebar();
    }


}