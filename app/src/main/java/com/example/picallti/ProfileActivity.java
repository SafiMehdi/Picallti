package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import data.User;
import okhttp3.ResponseBody;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_profile_page);

        ImageButton toggleButton1 = findViewById(R.id.sidebar_button_profile);
        toggleButton1.bringToFront();
        toggleButton1.setOnClickListener(new View.OnClickListener() {
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

        TextView bio =(TextView)findViewById(R.id.bioTextView);
        TextView nom = (TextView)findViewById(R.id.usernameTextView);
        TextView phone =  (TextView)findViewById(R.id.phoneNumberTextView);
        TextView email =  (TextView)findViewById(R.id.emailTextView);

        //Sidebar implementation
        Sidebar();





        //function to retrieve connected user
        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);

        //setting Info
        if(connectedUser != null) {
            String Nom = connectedUser.getNom();
            String Email = connectedUser.getEmail();
            int Telephone = connectedUser.getPhone();
            String Bio = connectedUser.getBio();
            int id = connectedUser.getId();

            bio.setText(Bio);
            nom.setText(Nom);
            phone.setText("0"+Integer.toString(Telephone));
            email.setText(Email);
            ImageView pp = (ImageView) findViewById(R.id.profile_picture);
            RetrofitService retrofitService = new RetrofitService();
            UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
            userApi.downloadImage(id).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        byte[] r;
                        try {
                            r = response.body().bytes();
                            Bitmap bitmap = BitmapFactory.decodeByteArray(r, 0, r.length);
                            pp.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

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