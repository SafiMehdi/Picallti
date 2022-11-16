package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras() ;

        TextView titre = findViewById(R.id.textView9);
        ImageView imageId = findViewById(R.id.imageView4);
        TextView localisation = findViewById(R.id.textView10);
        TextView time = findViewById(R.id.textView11);
        TextView prix = findViewById(R.id.textView13);
        TextView description = findViewById(R.id.textView14);

        titre.setText(extras.getString("titre"));
        //imageId.setImageDrawable(extras.get("imageId"));
        localisation.setText(extras.getString("localisation"));
        time.setText(extras.getString("time"));
        prix.setText(extras.getString("prix"));
        description.setText(extras.getString("description"));

    }
}