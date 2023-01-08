package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.logging.Level;
import java.util.logging.Logger;

import data.LoginRequest;
import data.User;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_page extends AppCompatActivity {
    private static final int RC_SIGN_IN = 201;
    private static final String TAG = "login_page";
    private CheckBox remembermecheckbox;
    private EditText username;
    private Button connect;
    private TextView signUp;
    private TextView forgotpass;
    private EditText emailaddress;
    private EditText password;
    public SharedPreferences Prefs;
    private ImageView connectwithfacebook;
    private ImageView connectwithgoogle;
    private ImageView connectwithtwitter;
    public static String PREFS_NAME = "myFile";
    public static User userTest;

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }



    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Binding components

        password = (EditText) findViewById(R.id.password);
        emailaddress = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        forgotpass = (TextView)findViewById(R.id.forgotbtn);
        signUp = (TextView) findViewById(R.id.inscription);
        connect = (Button)findViewById(R.id.btnConnect);
        remembermecheckbox = (CheckBox) findViewById(R.id.checkboxrememberme);
        Prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        connectwithfacebook = (ImageView) findViewById(R.id.connectwithfacebook);
        connectwithgoogle = (ImageView) findViewById(R.id.connectwithgoogle);
        connectwithtwitter = (ImageView) findViewById(R.id.connectwithtwitter);

        connectwithfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        connectwithgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singnIn();
            }
        });

        connectwithtwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_page.this, forgot_pass1.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_page.this, SingUp.class));
            }
        });
        if(remembermecheckbox.isChecked()){
            startActivity(new Intent(login_page.this, OffrePageActivity.class));
        }

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!emailaddress.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                LoginRequest userRequest = new LoginRequest(emailaddress.getText().toString(),password.getText().toString());
                userApi.loginUser(userRequest)
                        .enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {

                                if(response.isSuccessful()) {
                                    userTest = (User) response.body();
                                    SharedPreferences.Editor editor = Prefs.edit();
                                    if (remembermecheckbox.isChecked()) {
                                        Boolean BoolChecked = remembermecheckbox.isChecked();
                                        editor.putString("emailPref", emailaddress.getText().toString());
                                        editor.putString("passwordPref", password.getText().toString());
                                        editor.putBoolean("isCheckedPref", BoolChecked);
                                    }else {
                                        Prefs.edit().clear().apply();
                                    }

                                    //adding the user
                                    Gson gson = new Gson();
                                    String json = gson.toJson(userTest);
                                    editor.putString("connectedUser",json);
                                    editor.apply();

                                    Toast.makeText(getApplicationContext(), "Vous êtes connécté !", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(login_page.this, OffrePageActivity.class));

                                    System.out.println(userTest.getEmail());


                                }else if(response.code() == 500){
                                    Toast.makeText(getApplicationContext(),"Email ou mot de passe incorrect !",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Erreur serveur !",Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                System.out.println(emailaddress.getText().toString());
                                System.out.println(password.getText().toString());

                                Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, "Wrong Credentials !", t);
                                Toast.makeText(getApplicationContext(),"Email ou mot de passe incorrect !",Toast.LENGTH_LONG).show();
                            }
                        });}else{
                            Toast.makeText(getApplicationContext(),"Veuillez remplir les champs !",Toast.LENGTH_LONG).show();
                }


                /*if(remembermecheckbox.isChecked()){
                    SharedPreferences.Editor editor = Prefs.edit();
                    Boolean BoolChecked = remembermecheckbox.isChecked();
                    editor.putString("emailPref",emailaddress.getText().toString());
                    editor.putString("passwordPref",password.getText().toString());
                    editor.putBoolean("isCheckedPref",BoolChecked);
                    editor.apply();
                }
                else{
                    Prefs.edit().clear().apply();
                }
                if (username.getText().toString().equals("admin") &&
                        password.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Vous êtes connécté !",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(login_page.this, OffrePageActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }*/

            }
        });
        getPreferencesData();
    }

    private void getPreferencesData() {
        SharedPreferences myPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if(myPrefs.contains("emailPref")){
            String u = myPrefs.getString("emailPref","Not Found!");
            emailaddress.setText(u.toString());
        }
        if(myPrefs.contains("passwordPref")){
            String p = myPrefs.getString("passwordPref","Not Found!");
            password.setText(p.toString());
        }
        if(myPrefs.contains("isCheckedPref")){
            Boolean b = myPrefs.getBoolean("isCheckedPref",false);
            remembermecheckbox.setChecked(b);
        }
    }

    void singnIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();

            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(login_page.this, OffrePageActivity.class);
        startActivity(intent);
    }
}