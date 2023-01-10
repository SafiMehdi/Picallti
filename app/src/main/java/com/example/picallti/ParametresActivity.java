package com.example.picallti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParametresActivity extends AppCompatActivity {

    @BindView(R.id.switchNightMode)
    SwitchCompat switchNightMode;

    //The function that implements the sidebar
    public void Sidebar(){
        NavigationView navView = findViewById(R.id.sidebar_view);
        navView.inflateMenu(R.menu.sidebar_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent_profile = new Intent(ParametresActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(ParametresActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(ParametresActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(ParametresActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(ParametresActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_parametres_page);

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
        setContentView(R.layout.activity_parametres);
        ButterKnife.bind(this);
        //Sidebar implementation
        Sidebar();


        switchNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }

    @OnClick(R.id.changeLanguageSection)
    public void changeLanguageSection(){
        Intent intent = new Intent(this,LanguagesActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.aboutUsSection)
    public void aboutUsSection(){
        Intent intent = new Intent(this,AproposActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.deleteAccountSection)
    public void deleteaccountClicked(){
        Intent intent = new Intent(this,DeleteAccount.class);
        startActivity(intent);
    }

    @OnClick(R.id.changePasswordSection)
    public void changePasswordClicked(){
        Intent intent = new Intent(this,forgot_pass2.class);
        startActivity(intent);
    }

    @OnClick(R.id.contactUsSection)
    public void contactUsClicked(){
        Intent intent = new Intent(this,ContactActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.logOutSection)
    public void logOutClicked(){
        Toast.makeText( ParametresActivity.this, "You've been disconnected!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ParametresActivity.this, login_page.class));
    }

}