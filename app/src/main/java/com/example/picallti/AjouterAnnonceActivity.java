package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.time.LocalDate;
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
    @BindView(R.id.Operation)
    TextView operation;
    @BindView(R.id.Ville)
    TextView ville;
    @BindView(R.id.Categorie)
    TextView category;

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
    }

    @OnClick(R.id.button)
    public void saveOffre(){
        if(titre.getText().length() >100  ){
            Toast.makeText(getApplicationContext(),R.string.titleTooLongMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        if(titre.getText().length() == 0  ){
            Toast.makeText(getApplicationContext(),R.string.noTitleMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        if(prix.getText().length() == 0  ){
            Toast.makeText(getApplicationContext(),R.string.noPriceMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        if (Float.parseFloat(prix.getText().toString()) <=0){
            Toast.makeText(getApplicationContext(),R.string.falsePriceMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        String title = titre.getText().toString();
        String desc = description.getText().toString();
        float price = Float.parseFloat(prix.getText().toString());
        String op = operation.getText().toString();
        String city = ville.getText().toString();

        VehiculeType vehiculeType = new VehiculeType("typeV");
        Vehicule vehicule = new Vehicule("marque",vehiculeType);
        User user = new User("nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");


        RetrofitService retrofitService = new RetrofitService();
        OffreApi offreApi = retrofitService.getRetrofit().create(OffreApi.class);
        Call<List<Offre>> call = offreApi.getOffers();

        Offre offre = new Offre(R.drawable.avatar_2,title,desc,"localisation",price, LocalDate.now().toString(),op,user,vehicule,LocalDate.now().toString());
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

    @OnClick(R.id.add)
    public void addImage(){
        startActivity(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI));
    }


}