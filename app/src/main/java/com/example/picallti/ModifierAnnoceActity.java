package com.example.picallti;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.drawerlayout.widget.DrawerLayout;

import butterknife.BindView;
/*

public class ModifierAnnoceActity {


    String selectedOp = "";
    String selectedVille = "";
    String selectedCat = "";


    @BindView(R.id.TitreOffre)
    TextView titre;
     @BindView(R.id.Description)
    TextView description;
     @BindView(R.id.Prix)
    TextView prix;
     @BindView(R.id.add)
    ImageView addImage;

     String SelectedOp = "";
     String SelectedVille = "";
     String SelectedCat = "";



    public void operationValue(String selectedV) { this.selectedOp = selectedV; }
    public void villeValue(String selectedV) {this.selectedV = selectedV;}

    public void catValue(String selectedV){this.selectedOp = selectedV;}

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_update_annoce);
        @Override
        public void onClickListner(new View.onclickListner(){
            if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer((GravityCompat.START));
            }
            else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }



    @OnClick(R.id.button)
    public void saveOffre(){
        if(titre.getText().length() >100){
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
    }
})*/