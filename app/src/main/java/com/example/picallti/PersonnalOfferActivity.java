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
import adapters.MyOffersAdapter;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import adapters.FavoritesAdapter;
import adapters.MyOffersAdapter;
import adapters.OffresAdapter;
import butterknife.ButterKnife;
import data.Favoris;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;
import retrofit.OffreApi;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit.VehiculeApi;
import retrofit.VehiculeTypeApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonnalOfferActivity extends AppCompatActivity {

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
                        Intent intent_profile = new Intent(PersonnalOfferActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(PersonnalOfferActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(PersonnalOfferActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(PersonnalOfferActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(PersonnalOfferActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_personal_offer_page);

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
        setContentView(R.layout.activity_list_user_offer);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();
        ButterKnife.bind(this);

        recyclerView = findViewById(R.id.view_holder_favorites);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        VehiculeType vehiculeType = new VehiculeType("typeV");

        Vehicule vehicule = new Vehicule("marque",vehiculeType);

        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);

        //User user = new User("nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");
        //offres.add(  new Offre(R.drawable.motorcycle,"Motorcycle","A perfectly working Motorcycle, available starting from now ","localisation",67, "LocalTime.now()","vente",user,vehicule,  "LocalDate.of(2020, 1, 8)","kenitra"));
        //offres.add(new Offre("Bicycle VTT", "Vente",12,"A perfectly working bicycle, available starting from now ",  "bicycle",user,vehicule,"kenitra"));
        //offres.add(new data.Offre("Motor lahuma barik", "Swinga jaya mn asfi chi haja lahuma barik akhay diali", 50, "motorcycle"));
        //offres.add(new data.Offre("Boukchlita lhrba", "Hadi bla mandwi eliha , sl3a kadwi ela rasha asahbi", 10, "bicycle"));
        //offres.add(new Offre("Motor makaynch fhalu juj", "Had lmotor dor so9 kaml la l9iti bhalu aji dfl elia", 60, "motorcycle"));
        /*
        RetrofitService retrofitService = new RetrofitService();
        VehiculeTypeApi vehiculeTypeApi = retrofitService.getRetrofit().create(VehiculeTypeApi.class);
        VehiculeApi vehiculeApi = retrofitService.getRetrofit().create(VehiculeApi.class);
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);

        vehiculeTypeApi.getTypeByName(typeDeVehicule).enqueue(new Callback<VehiculeType>() {
            @Override
            public void onResponse(Call<VehiculeType> call, Response<VehiculeType> response) {
                VehiculeType vehiculeType = response.body();
                System.out.println("***************");
                System.out.println(vehiculeType);
                System.out.println("***************");
                Vehicule vehicule = new Vehicule(marque.getText().toString(), vehiculeType);
         */
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);

        //Call<List<Offre>> call = offreApi.getOffersByUser(user.getId());
        offreApi.getAllOffersByUser(connectedUser.getId()).enqueue(new Callback<List<Offre>>() {
            @Override
            public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                 //response.body() != null;
                if(response.body() != null){
                    System.out.println(response.body());
                    System.out.println("response khedmat dyal offre");
                    ArrayList<Offre> offres = new ArrayList<Offre>(response.body());
                    System.out.println(offres);
                    adapter =  new MyOffersAdapter(getApplicationContext(), offres);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    System.out.println("Response null");
                }
            }

            @Override
            public void onFailure(Call<List<Offre>> call, Throwable t) {
                Logger.getLogger(PersonnalOfferActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);
                System.out.println("Failure in my offers");
            }
        });



/*
        ArrayList<Favoris> favoris =new ArrayList<>();
        favoris.add(new Favoris(offres.get(0)));
        favoris.add(new Favoris(offres.get(1)));
        //favoris.add(new Favoris(offres.get(2)));
        //favoris.add(new Favoris(offres.get(3)));

        adapter=new FavoritesAdapter(favoris);
        recyclerView.setAdapter(adapter);
        //Sidebar implementation
        Sidebar();*/
    }


}