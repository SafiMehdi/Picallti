package com.example.picallti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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
                    case R.id.nav_logout:
                        Toast.makeText( ModifierProfileActivity.this, "You've been disconnected!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ModifierProfileActivity.this, login_page.class));
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
    }

}
/*

package com.example.picallti;

        import androidx.appcompat.app.AppCompatActivity;

        import android.net.Uri;
        import android.content.Intent;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Spinner;
        import android.widget.ArrayAdapter;
        import android.widget.Toast;
        import android.database.Cursor;
        import android.preference.PreferenceManager;
        import android.graphics.BitmapFactory;


public class ModifierProfileActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    public  static final int RESULT_LOAD_IMAGE = 1;
    public  static final int SELECT_PICTURE = 1;

    BottomBarFragment frag = new BottomBarFragment();
    Button changeProfilePictureButton;
    ImageView IVPreviewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_profile);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container, frag).commit();


        Spinner spinner = (Spinner) findViewById(R.id.cities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        changeProfilePictureButton = (Button) findViewById(R.id.changePictureBtn);
        changeProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String picturePath = PreferenceManager.getDefaultSharedPreferences(this).getString("picturePath", "");
                if (!picturePath.equals("")) {
                    IVPreviewImage = findViewById(R.id.IVPreviewImage);
                    IVPreviewImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                } else {
                    imageChooser();
                }
            }
        });
        //Sidebar implementation
        Sidebar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("picturePath", picturePath).commit();
            cursor.close();

            ImageView IVPreviewImage = (ImageView) findViewById(R.id.IVPreviewImage);
            IVPreviewImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
}
*/
