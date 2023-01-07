package com.example.picallti;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;


public class LanguagesActivity extends AppCompatActivity {

    //The function that implements the sidebar
    public void Sidebar(){
        NavigationView navView = findViewById(R.id.sidebar_view);
        navView.inflateMenu(R.menu.sidebar_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent_profile = new Intent(LanguagesActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(LanguagesActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(LanguagesActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(LanguagesActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(LanguagesActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    case R.id.nav_logout:
                        Toast.makeText( LanguagesActivity.this, "You've been disconnected!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LanguagesActivity.this, login_page.class));
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_languages_page);

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

    //Change lang function
    public void ChangeLanguage(){
        /*RadioGroup radioGroup = findViewById(R.id.languages_radio_grp);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.arabic_buttun:
                        Toast.makeText( LanguagesActivity.this, "you selected arabic",Toast.LENGTH_SHORT).show();
                        setAppLanguage("ar", "rMA");
                        break;
                    case R.id.french_buttun:
                        Toast.makeText( LanguagesActivity.this, "you selected french",Toast.LENGTH_SHORT).show();
                        setAppLanguage("fr", "rLU");
                        break;
                    case R.id.english_buttun:
                        Toast.makeText( LanguagesActivity.this, "you selected english",Toast.LENGTH_SHORT).show();
                        setAppLanguage("en", "rUS");
                        break;
                }
            }
        });*/
        Spinner spinner = findViewById(R.id.languages_spinner);
        final String[] Languages = {"Select Language", "Arabic", "French", "English"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if (selectedLang.equals("Arabic")){
                    setLocal(LanguagesActivity.this, "ar");
                    finish();
                    startActivity(getIntent());
                }else if (selectedLang.equals("English")){
                    setLocal(LanguagesActivity.this, "en");
                    finish();
                    startActivity(getIntent());
                }else if (selectedLang.equals("French")){
                    setLocal(LanguagesActivity.this, "fr");
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    /*private void setAppLanguage(String languageCode, String regionCode) {
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(languageCode, regionCode)); // API 17+ only.
        // Use conf.locale = new Locale(languageCode, regionCode) if targeting lower versions
        res.updateConfiguration(conf, dm);
        // Refresh the activity to apply the language change.
        recreate();
    }*/

   public void setLocal(Activity activity, String lanCode){
        Locale locale = new Locale(lanCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        //Sidebar implementation
        Sidebar();

        //Change lang function
        ChangeLanguage();
    }
}
