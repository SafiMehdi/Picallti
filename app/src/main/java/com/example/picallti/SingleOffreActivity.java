package com.example.picallti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import adapters.CommentsAdapter;
import adapters.NotificationAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.Commentaire;
import data.Notification;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;

public class SingleOffreActivity extends AppCompatActivity {

    @BindView(R.id.titreOffre)
    TextView titreOffre;
    @BindView(R.id.imageOffre)
    ImageView imageOffre;
    @BindView(R.id.prix)
    TextView prix;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.appeler)
    ImageButton appeler;
    @BindView(R.id.whatsapp)
    ImageButton whatsapp;
    @BindView(R.id.share)
    ImageButton share;
    @BindView(R.id.favoris)
    ImageButton like;
    int phoneNummber;

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_offre);


        recyclerView = findViewById(R.id.view_holder_comments);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        User user = new User(1,"nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");
        VehiculeType vehiculeType = new VehiculeType("typeV");
        Vehicule vehicule = new Vehicule("marque",vehiculeType);
        Offre offre = new Offre(R.drawable.motorcycle,"Motorcycle","A perfectly working Motorcycle, available starting from now ","localisation",67, LocalTime.now(),"vente",user,vehicule, LocalDate.of(2020, 1, 8));
        ArrayList<Commentaire> commentaires =new ArrayList<>();
        commentaires.add(new Commentaire("Wow this is a nice bike !!!",user,offre, LocalDate.now(),LocalTime.now()));
        commentaires.add(new Commentaire("Wow this is a nice bike !!!",user,offre, LocalDate.now(),LocalTime.now()));
        commentaires.add(new Commentaire("Wow this is a nice bike !!!",user,offre, LocalDate.now(),LocalTime.now()));

        adapter=new CommentsAdapter(getApplicationContext(),commentaires);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.appeler)
    public void callOwner(){
        System.out.println(phoneNummber);
        Uri phone = Uri.parse("tel:"+phoneNummber);
        Intent call = new Intent(Intent.ACTION_DIAL,phone);
        startActivity(call);
    }


}
