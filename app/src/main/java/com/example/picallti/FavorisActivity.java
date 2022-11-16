package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavorisActivity extends AppCompatActivity {

    @BindView(R.id.likesListView)
    ListView likesListView;
    @BindView(R.id.LikesBack)
    ImageView LikesBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
        ButterKnife.bind(this);

        int[] image = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};
        String[] title = {"Liked post", "Liked post", "Liked post", "Liked post", "Liked post", "Liked post", "Liked post"};
        String[] price = {"10 500 DH", "299 DH", "199 DH", "299 DH", "199 DH", "299 DH", "199 DH"};

        ArrayList<LikedPosts> likedPosts = new ArrayList<LikedPosts>();

        for (int i =0; i< price.length; i++){
            likedPosts.add(new LikedPosts(title[i], price[i], image[i]));
        }

        LikesBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        AdapterFavoris adapterFavoris = new AdapterFavoris(getApplicationContext(), R.layout.list_item, likedPosts);
        likesListView.setAdapter(adapterFavoris);
    }
}