package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.logging.Level;
import java.util.logging.Logger;

import data.User;
import retrofit.RetrofitService;
import retrofit.UserApi;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class ModifierProfileActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;

    BottomBarFragment frag = new BottomBarFragment();
    Button changeProfilePictureButton;
    Button saveEditBtn;
    ImageView IVPreviewImage;
    EditText changeBioInput , changePhoneInput , changeEmailInput ,changeSurnameInput , changeNameInput ;

    //The function that implements the sidebar
    public void Sidebar(){
        NavigationView navView = findViewById(R.id.sidebar_view);
        navView.inflateMenu(R.menu.sidebar_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent_profile = new Intent(ModifierProfileActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(ModifierProfileActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(ModifierProfileActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(ModifierProfileActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(ModifierProfileActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_modifier_profile);

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
        setContentView(R.layout.activity_modifier_profile);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();


        Spinner spinner = (Spinner) findViewById(R.id.cities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        changeProfilePictureButton = (Button) findViewById(R.id.changePictureBtn);
        changeProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked");
            }
        });

        changeNameInput = (EditText) findViewById(R.id.changeNameInput);
        changeSurnameInput = (EditText) findViewById(R.id.changeSurnameInput);
        changeEmailInput = (EditText) findViewById(R.id.changeEmailInput);
        changePhoneInput = (EditText) findViewById(R.id.changePhoneInput);
        changeBioInput = (EditText) findViewById(R.id.changeBioInput);
        saveEditBtn = (Button) findViewById(R.id.saveEditBtn);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        saveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateSurname() | !validateName() | !validatePhoneNo() | !validateEmail()) {
                    return;
                } else {
                    int img = 11;
                    String surname = changeSurnameInput.getText().toString();
                    String name = changeNameInput.getText().toString();
                    String mail = changeEmailInput.getText().toString();
                    String bio = changeBioInput.getText().toString();
                    int phone = Integer.parseInt(changePhoneInput.getText().toString());

                    //function to retrieve connected user
                    User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);
                    connectedUser.setNom(surname);
                    connectedUser.setPrenom(name);
                    connectedUser.setEmail(mail);
                    connectedUser.setBio(bio);
                    connectedUser.setPhone(phone);
                    connectedUser.setPhoto(img);

                    userApi.updateUser(connectedUser)
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    startActivity(new Intent(ModifierProfileActivity.this, login_page.class));
                                    Toast.makeText(ModifierProfileActivity.this, "Account Updated !", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Logger.getLogger(ModifierProfileActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);
                                }
                            });
                }
            }
        });
    }

    private Boolean validateSurname() {
        String val = changeSurnameInput.getText().toString();
        String validName = "(?=^[^0-9]+$)";
        if (val.isEmpty()) {
            changeSurnameInput.setError("Field cannot be empty");
            return false;
        } else if (val.matches(validName)) {
            changeSurnameInput.setError("Surname should not contain digits !");
            return false;
        } else {
            changeSurnameInput.setError(null);
            return true;
        }
    }

    private Boolean validateName() {
        String val = changeNameInput.getText().toString();
        String validName = "(?=^[^0-9]+$)";
        if (val.isEmpty()) {
            changeNameInput.setError("Field cannot be empty");
            return false;
        } else if (val.matches(validName)) {
            changeNameInput.setError("Name should not contain digits !");
            return false;
        } else {
            changeNameInput.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = changeEmailInput.getText().toString();
        String validEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            changeNameInput.setError("Field cannot be empty !");
            return false;
        } else if (!val.matches(validEmail)) {
            changeNameInput.setError("Invalid email address !");
            return false;
        } else {
            changeNameInput.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = changePhoneInput.getText().toString();
        String val2 = val.length() < 3 ? "00" : val.substring(0, 2);
        //System.out.println(val2);
        if (val.isEmpty()) {
            changePhoneInput.setError("Field cannot be empty !");
            return false;
        } else if (val.length() != 10) {
            changePhoneInput.setError("Only 10 digits are allowed !");
            return false;
        } else if (!val2.equals("06") && !val2.equals("07")) {
            changePhoneInput.setError("Phone number should start with 06 or 07 !");
            return false;
        } else {
            changePhoneInput.setError(null);
            return true;
        }
    }

}
