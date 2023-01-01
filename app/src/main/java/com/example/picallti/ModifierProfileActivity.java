package com.example.picallti;

import androidx.annotation.Nullable;
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


        /*Spinner spinner = (Spinner) findViewById(R.id.cities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/


        changeProfilePictureButton = (Button) findViewById(R.id.changePictureBtn);
        imgGallery = findViewById(R.id.imgGallery);
        changeProfilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery ,GALLERY_REQUEST_CODE );
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
                    int img = Integer.parseInt(changeProfilePictureButton.getText().toString());
                    String surname = changeSurnameInput.getText().toString();
                    String name = changeNameInput.getText().toString();
                    String mail = changeEmailInput.getText().toString();
                    String bio = changeBioInput.getText().toString();
                    int phone = Integer.parseInt(changePhoneInput.getText().toString());

                    //this should be replaced by un object user from the sharedPrefe
                    // user = sharedPrefVariable.get......

                  /*  userApi.updateUser(user)
                            .enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    startActivity(new Intent(ModifierProfileActivity.this, login_page.class));
                                    Toast.makeText(ModifierProfileActivity.this, "Account Updated !", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Logger.getLogger(ModifierProfileActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);
                                }
                            });*/
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                try {
                    Uri imageUri = data.getData();
                    Context context = ModifierProfileActivity.this;
                    filePath = RealPathUtil.getRealPath(context, imageUri);
                    //System.out.println("The path is : "+filePath);
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imgGallery.setImageBitmap(selectedImage);
                    RetrofitService retrofitService = new RetrofitService();
                    ImageDataApi imageDataApi = retrofitService.getRetrofit().create(ImageDataApi.class);
                    File file = new File(filePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                    imageDataApi.uploadImage(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Uploaded!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Not uploaded !!", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            System.out.println("U're here : " + t.toString());

                        }
                    });


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ModifierProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
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
