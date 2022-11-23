package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {


    BottomBarFragment frag = new BottomBarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras() ;

        TextView titre = findViewById(R.id.textView9);
        ImageView imageId = findViewById(R.id.imageView4);
        TextView localisation = findViewById(R.id.textView10);
        TextView time = findViewById(R.id.textView11);
        TextView prix = findViewById(R.id.textView13);
        TextView description = findViewById(R.id.textView14);

        titre.setText(extras.getString("titre"));
        int imId = extras.getInt("imageId");
        imageId.setImageResource(imId);
        localisation.setText(extras.getString("localisation"));
        time.setText(extras.getString("time"));
        prix.setText(extras.getString("prix"));
        description.setText(extras.getString("description"));

        LinearLayout offre_details_page = findViewById(R.id.linearLayout);
        ViewGroup.LayoutParams params = offre_details_page.getLayoutParams();
        params.height =(int) (getResources().getDisplayMetrics().heightPixels-getResources().getDisplayMetrics().heightPixels/9) ;
        offre_details_page.setLayoutParams(params);

    }
}