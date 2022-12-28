package com.example.picallti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_offre);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        titreOffre.setText(extras.getString("titre"));
        int photo = R.drawable.avatar_2;
        /*if(getResources().getResourceName((int)extras.getDouble("photo") ) != null){
            photo = extras.getInt("photo");
        }*/
        imageOffre.setBackgroundResource(photo);
        prix.setText(Double.toString(extras.getDouble("prix")));
        time.setText(extras.getString("time"));
        description.setText(extras.getString("description"));
        this.phoneNummber = extras.getInt("phone");
    }

    @OnClick(R.id.appeler)
    public void callOwner(){
        System.out.println(phoneNummber);
        Uri phone = Uri.parse("tel:"+phoneNummber);
        Intent call = new Intent(Intent.ACTION_DIAL,phone);
        startActivity(call);
    }
}
