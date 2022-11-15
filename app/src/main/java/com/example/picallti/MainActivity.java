package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ListView list;

    String[] maintitle ={
            "Velo BMX","Velo VTT",
    };

    String[] description ={
            "sqldkqsd qlskd qlsd qsd qslkdjqslk jdqsdia mlda q","sqldkqsd qlskd qlsd qsd qslkdjqslk jdqsdia mlda q",
    };

    String[] localisation ={
            "Rabat, Maroc", "Casablanca, Maroc","Kenitra, Maroc"
    };

    String[] prix ={
            "12DH/Jours", "10DH/Jours",
    };

    String[] temps ={
            "17 minutes", "2 Heures",
    };

    Integer[] imgid={
            R.drawable.profile_circle,R.drawable.profile_circle,
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AnnoncesListAdapter adapter=new AnnoncesListAdapter(this, maintitle, description, localisation, prix, temps, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }
}