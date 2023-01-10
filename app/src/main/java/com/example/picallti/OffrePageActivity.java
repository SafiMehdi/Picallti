package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;
import okhttp3.ResponseBody;
import retrofit.OffreApi;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffrePageActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private TextView title;
    private ArrayList<Offre> offres = new ArrayList<>();


    BottomBarFragment frag = new BottomBarFragment();

    //The function that implements the sidebar
    public void Sidebar() {
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
        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(), PREFS_NAME, "connectedUser", User.class);

        //setting title name
        title = findViewById(R.id.textView7);
        if (connectedUser != null) {
            String Nom = connectedUser.getNom();
            title.setText(Nom);
            int id = connectedUser.getId();
            ImageView profileimg = (ImageView) findViewById(R.id.imageView2);
            RetrofitService retrofitService = new RetrofitService();
            UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
            userApi.downloadImage(id).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        System.out.println("success");
                        byte[] r;
                        try {
                            r = response.body().bytes();
                            Bitmap bitmap = BitmapFactory.decodeByteArray(r, 0, r.length);
                            profileimg.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println(response.body());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.println("failed ! ");
                }
            });
        }

        ImageView img = (ImageView) findViewById(R.id.filter);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(OffrePageActivity.this, WhatYouNeedActivity.class));
            }
        });

        recyclerView = findViewById(R.id.view_holder_offers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        RetrofitService retrofitService = new RetrofitService();
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);
        Bundle extras = getIntent().getExtras();
        String type = "Home";
        if(extras != null){
            if(extras.containsKey("type")){
                type = extras.getString("type");
            }
        }
        System.out.println("************");
        System.out.println(type);
        System.out.println("************");
        switch (type){
            case "vehiculeType":
                System.out.println("************Tyyyyyyyyyyyype");
                switch (extras.getString("value")){
                    case "Vélo":
                        offreApi.getOffersByVehiculeType("Vélo").enqueue(new Callback<List<Offre>>() {
                            @Override
                            public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {

                                offres = new ArrayList<Offre>(response.body());
                                System.out.println(offres);
                                adapter = new OffresAdapter(getApplicationContext(), offres);
                                recyclerView.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<List<Offre>> call, Throwable t) {
                                System.out.println("can't get Vélo offers");
                            }
                        });
                        break;
                    case "Vélo_electrique":
                        offreApi.getOffersByVehiculeType("Vélo électrique").enqueue(new Callback<List<Offre>>() {
                            @Override
                            public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {

                                offres = new ArrayList<Offre>(response.body());
                                System.out.println(offres);
                                adapter = new OffresAdapter(getApplicationContext(), offres);
                                recyclerView.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<List<Offre>> call, Throwable t) {
                                System.out.println("can't get Vélo offers");
                            }
                        });
                        break;
                    case "Moto":
                        offreApi.getOffersByVehiculeType("Moto").enqueue(new Callback<List<Offre>>() {
                            @Override
                            public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                                offres = new ArrayList<Offre>(response.body());
                                System.out.println(offres);
                                adapter = new OffresAdapter(getApplicationContext(), offres);
                                recyclerView.setAdapter(adapter);
                            }
                            @Override
                            public void onFailure(Call<List<Offre>> call, Throwable t) {
                                System.out.println("can't get Vélo offers");
                            }
                        });
                        break;
                    case "Scooter":
                        offreApi.getOffersByVehiculeType("Scooter").enqueue(new Callback<List<Offre>>() {
                            @Override
                            public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {

                                offres = new ArrayList<Offre>(response.body());
                                System.out.println(offres);
                                adapter = new OffresAdapter(getApplicationContext(), offres);
                                recyclerView.setAdapter(adapter);
                            }

                            @Override
                            public void onFailure(Call<List<Offre>> call, Throwable t) {
                                System.out.println("can't get Vélo offers");
                            }
                        });
                        break;
                }
                break;
            case "price":
                float min = extras.getFloat("min");
                float max = extras.getFloat("max");
                offreApi.filterOffresByPrix(min,max).enqueue(new Callback<List<Offre>>() {
                    @Override
                    public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                        offres = new ArrayList<Offre>(response.body());
                        System.out.println(offres);
                        adapter = new OffresAdapter(getApplicationContext(), offres);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Offre>> call, Throwable t) {
                        System.out.println("can't get offers by price");
                    }
                });
                break;
            case "city":
                String ville = extras.getString("ville");
                offreApi.getOffreByVille(ville).enqueue(new Callback<List<Offre>>() {
                    @Override
                    public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                        offres = new ArrayList<Offre>(response.body());
                        System.out.println(offres);
                        adapter = new OffresAdapter(getApplicationContext(), offres);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Offre>> call, Throwable t) {
                        System.out.println("can't get offers by city");
                    }
                });
                break;
            case "date":
                offreApi.getOffreByDateDesc().enqueue(new Callback<List<Offre>>() {
                    @Override
                    public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                        offres = new ArrayList<Offre>(response.body());
                        System.out.println(offres);
                        adapter = new OffresAdapter(getApplicationContext(), offres);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Offre>> call, Throwable t) {
                        System.out.println("can't get recent offers");

                    }
                });
                break;
            case "Home":
                System.out.println("************");
                System.out.println("Hooooooooooooome");
                System.out.println("************");
                Call<List<Offre>> call = offreApi.getOffers();
                call.enqueue(new Callback<List<Offre>>() {
                        @Override
                        public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                            assert response.body() != null;
                            //System.out.println(response.body().toString() );
                            System.out.println("working");
                            offres = new ArrayList<Offre>(response.body());
                            System.out.println(offres);
                            adapter = new OffresAdapter(getApplicationContext(), offres);
                            recyclerView.setAdapter(adapter);

                            //Sidebar implementation
                            Sidebar();
                    }


                    @Override
                    public void onFailure(Call<List<Offre>> call, Throwable t) {
                        System.out.println("exceeeption");
                        Logger.getLogger(SingUp.class.getName()).log(Level.SEVERE, "Error Occured", t);
                    }
                });
        }

        EditText search = findViewById(R.id.editTextTextPersonName2);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    offreApi.searchTitleContaining(search.getText().toString()).enqueue(new Callback<List<Offre>>() {
                        @Override
                        public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                            offres = new ArrayList<Offre>(response.body());
                            System.out.println(offres);
                            adapter = new OffresAdapter(getApplicationContext(), offres);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<Offre>> call, Throwable t) {

                        }
                    });
                    return true;
                }
                return false;
            }
        });



    }


}