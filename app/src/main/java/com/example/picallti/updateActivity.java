package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.logging.Level;
import java.util.logging.Logger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

import butterknife.ButterKnife;
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
import retrofit2.Response;
import retrofit2.Callback;

import android.os.Bundle;

public class updateActivity extends AppCompatActivity {

    EditText titreEdit, descEdit, prixEdit, marqueEdit;
    ImageView imgEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_annonce);
        //getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();

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
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        ButterKnife.bind(this);

        Button saveEditBtn = (Button) findViewById(R.id.button);


        titreEdit = (EditText) findViewById(R.id.TitreOffre);
        descEdit = (EditText) findViewById(R.id.Description);
        prixEdit = (EditText) findViewById(R.id.Prix);
        marqueEdit = (EditText) findViewById(R.id.Marque);
        //imgEdit = (Image) findViewById(R.id.add);



        RetrofitService retrofitService = new RetrofitService();
        VehiculeTypeApi vehiculeTypeApi = retrofitService.getRetrofit().create(VehiculeTypeApi.class);
        VehiculeApi vehiculeApi = retrofitService.getRetrofit().create(VehiculeApi.class);
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);
        saveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titre1 = titreEdit.getText().toString();
                String description1 = descEdit.getText().toString();
                float prix1 = Float.parseFloat(prixEdit.getText().toString());
                String ville = spinner.getSelectedItem().toString();
                String operation = spinner2.getSelectedItem().toString();
                String categorie = spinner.getSelectedItem().toString();


                Offre offre = new Offre();

                offre.setDescription(description1);
                offre.setTitre(titre1);
                offre.setPrix(prix1);
                offre.setVille(ville);
                offre.setOperation(operation);

                offreApi.update(offre)
                                .enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        //startActivity(updateActivity.this, HomePageActivity.class);
                                        Toast.makeText(updateActivity.this, "Offre Updated !", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Logger.getLogger(updateActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);

                                    }
                                });
            }
        });


    }
}
