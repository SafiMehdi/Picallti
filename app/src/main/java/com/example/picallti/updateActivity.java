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

import adapters.MyOffersAdapter;
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
        setContentView(R.layout.activity_update_annonce);
        //getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();

        //User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);
        //Offre offreSelected =

        Spinner spinner2 = (Spinner) findViewById(R.id.Categorie);
        Spinner spinner3 = (Spinner) findViewById(R.id.Operation);
        Spinner spinner = (Spinner) findViewById(R.id.Ville);

        Bundle extras = getIntent().getExtras();

        //populating spinner 1
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Ville, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int selectionPosition= adapter.getPosition(extras.getString("ville"));
        spinner.setAdapter(adapter);

        //populating spinner 2
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.categorie, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int selectionPosition2= adapter2.getPosition(extras.getString("type"));
        spinner2.setAdapter(adapter2);


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.operation, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int selectionPosition3= adapter3.getPosition(extras.getString("operation"));
        spinner3.setAdapter(adapter3);

        ButterKnife.bind(this);

        Button saveEditBtn = (Button) findViewById(R.id.button);


        titreEdit = (EditText) findViewById(R.id.TitreOffre);
        descEdit = (EditText) findViewById(R.id.Description);
        prixEdit = (EditText) findViewById(R.id.Prix);
        marqueEdit = (EditText) findViewById(R.id.Marque);
        spinner3.post(new Runnable() {
            @Override
            public void run() {
                spinner3.setSelection(selectionPosition3);
            }
        });

        spinner2.post(new Runnable() {
            @Override
            public void run() {
                spinner2.setSelection(selectionPosition2);
            }
        });

        spinner.post(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(selectionPosition);
            }
        });

        //imgEdit = (Image) findViewById(R.id.add);


        titreEdit.setText(extras.getString("titre"));
        descEdit.setText(extras.getString("description"));
        marqueEdit.setText(extras.getString("vehicule"));
        prixEdit.setText(Float.toString(extras.getFloat("prix")));

       // ArrayAdapter<String> adapterr = (ArrayAdapter<String>) spinner3.getAdapter();


        //System.out.println(selectionPosition);

        /*int spinnerPosition = adapter.getPosition(extras.getString("operation"));
        spinner.setSelection(adapter.getPosition(extras.getString("operation")));*/
        //spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(extras.getString("operation")));

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
                String operation = spinner3.getSelectedItem().toString();
                String categorie = spinner2.getSelectedItem().toString();


                Offre offre = new Offre();
                //offre.setId(extras.getInt("id"));

                offre.setDescription(description1);
                offre.setTitre(titre1);
                offre.setPrix(prix1);
                offre.setVille(ville);
                offre.setOperation(operation);

                offreApi.update(offre)
                                .enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        startActivity(new Intent (updateActivity.this, HomePageActivity.class));
                                        Toast.makeText(updateActivity.this, "Offre Updated !", Toast.LENGTH_SHORT).show();
                                        //startActivity(new Intent(ModifierProfileActivity.this, ProfileActivity.class));

                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Logger.getLogger(updateActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);

                                    }
                                });
            }
        });



        /*
         RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        saveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String surname = changeSurnameInput.getText().toString();
                    String name = changeNameInput.getText().toString();
                    String mail = changeEmailInput.getText().toString();
                    String bio = changeBioInput.getText().toString();
                    int phone = Integer.parseInt(changePhoneInput.getText().toString());

                    connectedUser.setNom(surname);
                    connectedUser.setPrenom(name);
                    connectedUser.setEmail(mail);
                    connectedUser.setBio(bio);
                    connectedUser.setPhone(phone);

                    userApi.updateUser(connectedUser)
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    startActivity(new Intent(ModifierProfileActivity.this, ProfileActivity.class));
                                    Toast.makeText(ModifierProfileActivity.this, "Account Updated !", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Logger.getLogger(ModifierProfileActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);
                                }
                            });
         */


    }
}
