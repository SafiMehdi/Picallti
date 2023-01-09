package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import androidx.annotation.Nullable;
import static com.example.picallti.login_page.PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
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
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import data.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit.ImageDataApi;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class ModifierProfileActivity extends AppCompatActivity {

    private final int GALLERY_REQUEST_CODE = 1000;
    public static final int PICK_IMAGE = 1;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    BottomBarFragment frag = new BottomBarFragment();
    Button changeProfilePictureButton;
    Button saveEditBtn;
    ImageView imgGallery;
    EditText changeBioInput , changePhoneInput , changeEmailInput ,changeSurnameInput , changeNameInput ;
    String filePath;



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

        changeNameInput = (EditText) findViewById(R.id.changeNameInput);
        changeSurnameInput = (EditText) findViewById(R.id.changeSurnameInput);
        changeEmailInput = (EditText) findViewById(R.id.changeEmailInput);
        changePhoneInput = (EditText) findViewById(R.id.changePhoneInput);
        changeBioInput = (EditText) findViewById(R.id.changeBioInput);
        saveEditBtn = (Button) findViewById(R.id.saveEditBtn);

        //function to retrieve connected user
        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);

        //setting Info
        if(connectedUser != null) {
            String Nom = connectedUser.getNom();
            String Preom = connectedUser.getPrenom();
            String Email = connectedUser.getEmail();
            int Telephone = connectedUser.getPhone();
            String Bio = connectedUser.getBio();

            changeSurnameInput.setText(Nom);
            changeNameInput.setText(Preom);
            changeEmailInput.setText(Email);
            changeBioInput.setText(Bio);
            changePhoneInput.setText("0"+String.valueOf(Telephone));
        }

        changeProfilePictureButton = (Button) findViewById(R.id.changePictureBtn);
        imgGallery = findViewById(R.id.imageView2);
        changeProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery ,GALLERY_REQUEST_CODE );
                //System.out.println("Clicked");
            }

        });


        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        saveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateSurname() | !validateName() | !validatePhoneNo() | !validateEmail()) {
                    return;
                } else {
                    String surname = changeSurnameInput.getText().toString();
                    String name = changeNameInput.getText().toString();
                    String mail = changeEmailInput.getText().toString();
                    String bio = changeBioInput.getText().toString();
                    int phone = Integer.parseInt(changePhoneInput.getText().toString());

                    connectedUser.setNom(surname);
                    connectedUser.setPrenom(name);
                    connectedUser.setEmail(mail);
                    connectedUser.setBio(bio);
                    connectedUser.setPhone(phone);

                    userApi.updateUser(connectedUser)
                            .enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    startActivity(new Intent(ModifierProfileActivity.this, ProfileActivity.class));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //function to retrieve connected user
        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);

        if (resultCode == RESULT_OK) {
            int permission = ActivityCompat.checkSelfPermission(ModifierProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        ModifierProfileActivity.this,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );
            }else {
                    Uri imageUri = data.getData();
                    Context context = ModifierProfileActivity.this;
                    filePath = RealPathUtil.getRealPath(context, imageUri);
                    System.out.println("The path is : "+filePath);
                    // Display the image
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imgGallery.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                    //######
                    File file = new File(filePath);
                    String ext = filePath.substring(filePath.lastIndexOf(".")); // Extension with dot .jpg, .png
                System.out.println("the extension:"+ext);
                if(ext.equals(".jpg") || ext.contains(".png")){
                        RetrofitService retrofitService = new RetrofitService();
                        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
                        RequestBody requestFile;
                        if(ext.equals(".png")){
                            System.out.println("here is png");
                            requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                        }else {
                            requestFile = RequestBody.create(MediaType.parse("image/png"), file);
                        }
                        String email = connectedUser.getEmail();
                        int id = connectedUser.getId();
                        MultipartBody.Part body = MultipartBody.Part.createFormData("image",file.getName(), requestFile);
                        userApi.updateUserWithImage(id, body).enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Uploaded!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Not uploaded !!", Toast.LENGTH_SHORT).show();
                                    System.out.println(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                                System.out.println("U're here : " + t.toString());

                            }
                        });
                    } else {
                        Toast.makeText(ModifierProfileActivity.this, "Image type not supported !!",Toast.LENGTH_LONG).show();
                    }


            }}
        else {
                Toast.makeText(ModifierProfileActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }



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
