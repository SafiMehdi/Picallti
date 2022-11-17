package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    ListView list;
    Button modifierProfileButton;

    String[] maintitle ={
            "Velo BMX","Velo VTT","Chi pikala zwina"
    };

    String[] description ={
            "sqldkqsd qlskd qlsd qsd qslkdjqslk jdqsdia mlda q","sqldkqsd qlskd qlsd qsd qslkdjqslk jdqsdia mlda q","sqldkqsd qlskd qlsd qsd qslkdjqslk jdqsdia mlda q"
    };

    String[] localisation ={
            "Rabat, Maroc", "Casablanca, Maroc","Kenitra, Maroc"
    };

    String[] prix ={
            "12DH/Jours", "10DH/Jours","8DH/Jours"
    };

    String[] temps ={
            "17 minutes", "2 Heures","2 Jours"
    };

    Integer[] imgid={
            R.drawable.profile_circle,R.drawable.profile_circle,R.drawable.profile_circle
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AnnoncesListAdapter adapter=new AnnoncesListAdapter(this, maintitle, description, localisation, prix, temps, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        modifierProfileButton =(Button)findViewById(R.id.modifierProfileButton);

        modifierProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Profile.this, ModifierProfile.class);
                Profile.this.startActivity(activityChangeIntent);
            }
        });
    }
}