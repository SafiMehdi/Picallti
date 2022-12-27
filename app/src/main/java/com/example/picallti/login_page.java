package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class login_page extends AppCompatActivity {
    private CheckBox remembermecheckbox;
    private EditText username;
    private Button connect;
    private TextView signUp;
    private TextView forgotpass;
    private EditText emailaddress;
    private EditText password;
    public SharedPreferences Prefs;
    public static String PREFS_NAME = "myFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Binding components
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        emailaddress = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        forgotpass = (TextView)findViewById(R.id.forgotbtn);
        signUp = (TextView) findViewById(R.id.inscription);
        connect = (Button)findViewById(R.id.btnConnect);
        remembermecheckbox = (CheckBox) findViewById(R.id.checkboxrememberme);
        Prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);



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

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(remembermecheckbox.isChecked()){
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
                }

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
}