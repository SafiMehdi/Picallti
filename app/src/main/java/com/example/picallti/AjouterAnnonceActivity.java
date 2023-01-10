package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
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
import retrofit.VehiculeTypeApi;
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
    VehiculeType vehiculeType;


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
        Spinner spinner3 = (Spinner) findViewById(R.id.Operation);
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
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.operation, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

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

        String title = titre.getText().toString();
        String desc = description.getText().toString();
        float price = Float.parseFloat(prix.getText().toString());
        //String op = this.selectedOp;
        Spinner operation = (Spinner) findViewById(R.id.Operation);
        Spinner ville = findViewById(R.id.Ville);
        Spinner type = findViewById(R.id.Categorie);
        String typeDeVehicule = type.getSelectedItem().toString();
        String op = operation.getSelectedItem().toString();
        String city = ville.getSelectedItem().toString();

        //VehiculeType vehiculeType = new VehiculeType(1, "typeV");
        User user = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);


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
            }

            @Override
            public void onFailure(Call<VehiculeType> call, Throwable t) {
                VehiculeType vehiculeType = new VehiculeType(30,"no type");
                Vehicule vehicule = new Vehicule(marque.getText().toString(), vehiculeType);
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
            }
        });


        vehiculeApi.getLastVehicule().enqueue(new Callback<Vehicule>() {
            @Override
            public void onResponse(Call<Vehicule> call, Response<Vehicule> response) {
                Vehicule vehicule1 = response.body();
                vehicule1.setId(vehicule1.getId()+1);
                Offre offre = new Offre(R.drawable.avatar_2, title, desc, "localisation", price, LocalTime.now().toString(), op, user, vehicule1, LocalDate.now().toString(), city);

                System.out.println(new Gson().toJson(offre));
                offreApi.addOffre(offre)
                        .enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                // startActivity(new Intent(SingUp.this, login_page.class));
                                Toast.makeText(AjouterAnnonceActivity.this, "Offer Created !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AjouterAnnonceActivity.this,SingleOffreActivity.class);
                                intent.putExtra("photo",R.drawable.avatar_5);
                                intent.putExtra("titre", offre.getTitre());
                                intent.putExtra("prix", offre.getPrix());
                                String date = "";
                                if (offre.getLocalDateTime() != null){
                                    date = offre.getLocalDateTime();
                                }
                                intent.putExtra("date", date);
                                intent.putExtra("id", offre.getId());
                                intent.putExtra("operation", offre.getOperation());
                                intent.putExtra("localisation", offre.getLocalisation());
                                intent.putExtra("description",offre.getDescription());
                                String time = "";
                                if(offre.getLocalDateTime() != null & offre.getTime()!= null){
                                    time =   offre.getLocalDateTime() +  " "+offre.getTime().substring(0,5);
                                }
                                else {
                                    if(offre.getTime()!= null){
                                        time =   offre.getLocalDateTime() ;
                                    }
                                    if(offre.getLocalDateTime()!= null){
                                        time =   offre.getLocalDateTime();
                                    }
                                }
                                intent.putExtra("time",time);
                                String username = "";
                                int phone = 0;
                                if(offre.getUser() != null){
                                    username = offre.getUser().getNom();
                                    phone = offre.getUser().getPhone();
                                }
                                intent.putExtra("user",username);
                                intent.putExtra("phone",phone);
                                String marque = "";
                                if(offre.getVehicule() != null){
                                    marque = offre.getVehicule().getMarque();
                                }
                                intent.putExtra("vehicule", marque);
                                startActivity(intent);
                                //startActivity(new Intent(AjouterAnnonceActivity.this,));
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
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),3);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri image = data.getData();


        }
    }
}