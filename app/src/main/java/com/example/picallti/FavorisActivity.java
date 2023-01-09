package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import adapters.FavoritesAdapter;
import adapters.OffresAdapter;
import data.Favoris;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;
import retrofit.FavorisApi;
import retrofit.OffreApi;
import retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavorisActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
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
                        Intent intent_profile = new Intent(FavorisActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(FavorisActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(FavorisActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(FavorisActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(FavorisActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_favoris_page);

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
        setContentView(R.layout.activity_favoris);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();

        recyclerView = findViewById(R.id.view_holder_favorites);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        //function to retrive connected User
        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);
        
        int idConnectedUser = 0;
        if(connectedUser != null){
            idConnectedUser = connectedUser.getId();
        }

        RetrofitService retrofitService = new RetrofitService();
        FavorisApi favorisApi = retrofitService.getRetrofit().create(FavorisApi.class);
        Call<List<Favoris>> callFavoris = favorisApi.getFavorisByUser(idConnectedUser);
        callFavoris.enqueue(new Callback<List<Favoris>>() {
            @Override
            public void onResponse(Call<List<Favoris>> call, Response<List<Favoris>> response) {
                if(response.isSuccessful()) {
                    System.out.println("IT'S WORKING !!!!!!!!!!");

                    ArrayList<Favoris> favoris = new ArrayList<Favoris>(response.body());
                    System.out.println(favoris);
                    adapter = new FavoritesAdapter(favoris);
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(getApplicationContext(),"Erreur Serveur !",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Favoris>> call, Throwable t) {
                System.out.println("Request Error !");
                Toast.makeText(getApplicationContext(),"Erreur serveur !",Toast.LENGTH_LONG).show();
            }
        });

        //Sidebar implementation
        Sidebar();
    }
}