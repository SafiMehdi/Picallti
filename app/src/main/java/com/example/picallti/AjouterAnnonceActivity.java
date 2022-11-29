package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AjouterAnnonceActivity extends AppCompatActivity {


    @BindView(R.id.TitreOffre)
    TextView titre;
    @BindView(R.id.Description)
    TextView description;
    @BindView(R.id.Prix)
    TextView prix;
    @BindView(R.id.add)
    ImageView addImage;

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
    }

    @OnClick(R.id.add)
    public void addImage(){
        startActivity(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI));
    }
}