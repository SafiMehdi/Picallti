package com.example.picallti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ModifierProfileActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;

    BottomBarFragment frag = new BottomBarFragment();
    Button changeProfilePictureButton;
    ImageView IVPreviewImage;

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
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        changeProfilePictureButton = (Button) findViewById(R.id.changePictureBtn);

        changeProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                Intent activityChangeIntent = new Intent(MainActivity.this, ListsView.class);
                MainActivity.this.startActivity(activityChangeIntent);*/
                System.out.println("Clicked");
                imageChooser();
            }
        });
        //Sidebar implementation
        Sidebar();
    }
        void imageChooser () {
            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");
            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");
            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
            startActivityForResult(chooserIntent, PICK_IMAGE);
        };

        public void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                    // compare the resultCode with the
                    // SELECT_PICTURE constant
                if (requestCode == PICK_IMAGE) {
                        // Get the url of the image from data
                    Uri selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        // update the preview image in the layout
                        IVPreviewImage.setImageURI(selectedImageUri);
                    }
                }
            }
        }
    }
