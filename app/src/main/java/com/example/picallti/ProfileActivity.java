package com.example.picallti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    BottomBarFragment frag = new BottomBarFragment();

    //The function that implements the sidebar
    public void Sidebar(){
        NavigationView navView = findViewById(R.id.sidebar_view);
        navView.inflateMenu(R.menu.sidebar_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent_profile = new Intent(ProfileActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(ProfileActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(ProfileActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(ProfileActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(ProfileActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_offre_page);

        ImageButton toggleButton = findViewById(R.id.sidebar_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open or close the navigation drawer when the button is clicked
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();
        ImageView img = (ImageView) findViewById(R.id.profile_picture);
        img.setBackgroundResource(R.drawable.background_profile_picutre);
        ButterKnife.bind(this);

        //Sidebar implementation
        Sidebar();
    }

    @OnClick(R.id.addOfferButton)
    public void addClick(){
        Intent intent = new Intent(this,AjouterAnnonceActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.settingsBtn)
    public void settingsClick(){
        Intent intent = new Intent(this,ParametresActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.editProfileBtn)
    public void editClick(){
        Intent intent = new Intent(this,ModifierProfileActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.OffresBtn)
    public void OffresClick(){
        Intent intent = new Intent(this,PersonnalOfferActivity.class);
        startActivity(intent);
    }


}