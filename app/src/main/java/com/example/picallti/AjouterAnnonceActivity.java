package com.example.picallti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import com.google.gson.Gson;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataBase.PicalltiDbHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;
import retrofit.OffreApi;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit.VehiculeApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AjouterAnnonceActivity extends AppCompatActivity {


    @BindView(R.id.TitreOffre)
    TextView titre;
    @BindView(R.id.Description)
    TextView description;
    @BindView(R.id.Prix)
    TextView prix;
    @BindView(R.id.add)
    ImageView addImage;
    @BindView(R.id.Marque)
    TextView marque;
    /*@BindView(R.id.Operation)
    Spinner operation;
    @BindView(R.id.Ville)
    TextView ville;
    @BindView(R.id.Categorie)
    TextView category;*/

    String selectedOp = "";
    String selectedVille = "";
    String selectedCat = "";


    public void operationValue(String selectedV) {
        this.selectedOp = selectedV;
    }

    public void villeValue(String selectedV) {
        this.selectedOp = selectedV;
    }

    public void catValue(String selectedV) {
        this.selectedOp = selectedV;
    }

    //The function that implements the sidebar
    public void Sidebar() {
        NavigationView navView = findViewById(R.id.sidebar_view);
        navView.inflateMenu(R.menu.sidebar_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent_profile = new Intent(AjouterAnnonceActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(AjouterAnnonceActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(AjouterAnnonceActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(AjouterAnnonceActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(AjouterAnnonceActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_ajouter_annoce);

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
        setContentView(R.layout.activity_ajouter_annonce);

        //Binding view components
        Spinner spinner2 = (Spinner) findViewById(R.id.Categorie);
        Spinner spinner = (Spinner) findViewById(R.id.Ville);

        //populating spinner 1
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Ville, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //populating spinner 2
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.categorie, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        ButterKnife.bind(this);

        PicalltiDbHelper db = new PicalltiDbHelper(getApplicationContext());
        db.noteDbHelper.deleteAll();
        System.out.println("---------------------------------------");
        try {
            System.out.println(db.noteDbHelper.readNotes().size());
            System.out.println(db.favorisDbHelper.readFavoris().size());
            db.favorisDbHelper.deleteAll();
            System.out.println(db.favorisDbHelper.readFavoris().size());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------");
        //Sidebar implementation
        Sidebar();
    }

    @OnClick(R.id.button)
    public void saveOffre() {
        if (titre.getText().length() > 100) {
            Toast.makeText(getApplicationContext(), R.string.titleTooLongMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        if (titre.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), R.string.noTitleMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        if (prix.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), R.string.noPriceMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        if (Float.parseFloat(prix.getText().toString()) <= 0) {
            Toast.makeText(getApplicationContext(), R.string.falsePriceMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        Spinner operation = findViewById(R.id.Operation);
        /*operation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                operationValue(String.valueOf(adapterView.getItemIdAtPosition(position)));
            }
        });*/
        operation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                operationValue(String.valueOf(parent.getItemIdAtPosition(position)));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner ville = findViewById(R.id.Ville);
        ville.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                villeValue(String.valueOf(parent.getItemIdAtPosition(position)));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner category = findViewById(R.id.Categorie);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catValue(String.valueOf(parent.getItemIdAtPosition(position)));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String title = titre.getText().toString();
        String desc = description.getText().toString();
        float price = Float.parseFloat(prix.getText().toString());
        String op = this.selectedOp;
        String city = this.selectedVille;

        VehiculeType vehiculeType = new VehiculeType(1, "typeV");
        Vehicule vehicule = new Vehicule(marque.getText().toString(), vehiculeType);
        User user = new User(1, "nom", "prenom", "M", "testttt@test.com", 78, "pass", 78, "bio", "admin");


        RetrofitService retrofitService = new RetrofitService();
        VehiculeApi vehiculeApi = retrofitService.getRetrofit().create(VehiculeApi.class);
        vehiculeApi.addVehicule(vehicule).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("working");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("erreuuuuuuuuuuur");

            }
        });
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);

        vehiculeApi.getLastVehicule().enqueue(new Callback<Vehicule>() {
            @Override
            public void onResponse(Call<Vehicule> call, Response<Vehicule> response) {
                Vehicule vehicule1 = response.body();
                vehicule1.setId(vehicule1.getId()+1);
                Offre offre = new Offre(R.drawable.avatar_2, title, desc, "localisation", price, LocalTime.now().toString(), op, user, vehicule1, LocalDate.now().toString(), "ville");

                System.out.println(new Gson().toJson(offre));
                offreApi.addOffre(offre)
                        .enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                // startActivity(new Intent(SingUp.this, login_page.class));
                                Toast.makeText(AjouterAnnonceActivity.this, "Account Created !", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Logger.getLogger(AjouterAnnonceActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);
                            }
                        });
            }

            @Override
            public void onFailure(Call<Vehicule> call, Throwable t) {
                System.out.println("Can't get last vehicule");

            }
        });

    }

    @OnClick(R.id.add)
    public void addImage() {
        startActivity(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI));
    }


}