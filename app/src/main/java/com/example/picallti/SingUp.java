package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import MailAPI2.GMailSender;

public class SingUp extends AppCompatActivity {
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //Binding components
        Button signup = (Button) findViewById(R.id.createAccountButton);
        ImageView back = (ImageView) findViewById(R.id.back);
        email = (EditText) findViewById(R.id.email);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingUp.this, login_page.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SingUp.this, "Account Created !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SingUp.this, login_page.class));
                sendMessage();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.cities_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    private void sendMessage() {
        String bodyText = "Mail de creation de compte , TEST";
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("picalltiservice@gmail.com", "ekvzpeijpsfxfjxg");
                    sender.sendMail("Welcome to Picallti APP",
                            bodyText,
                            "picalltiservice@gmail.com",
                            email.getText().toString());

                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());

                }
            }
        });
        sender.start();
    }
}