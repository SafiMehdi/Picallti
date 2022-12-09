package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


import MailAPI2.GMailSender;

public class forgot_pass1 extends AppCompatActivity {

    private EditText mEmail;
    private TextView txtbacklogin;
    private Button sendMailButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass1);

        //Binding components
        mEmail = (EditText)findViewById(R.id.edit);
        TextView txtotp = (TextView) findViewById(R.id.otp);
        txtbacklogin = (TextView)findViewById(R.id.txtbacklogin);
        sendMailButton = (Button)findViewById(R.id.btn);

        txtotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(forgot_pass1.this, OTPActivity.class));
            }
        });
        txtbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(forgot_pass1.this, login_page.class));
            }
        });

        sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validEmail(mEmail.getText().toString())) {
                    Toast.makeText(forgot_pass1.this,"Veuillez saisir un mail valide !",Toast.LENGTH_LONG).show();

                }
                else{
                    sendMessage();
                }

            }
        });




    }

    //Mail Sending Function

    private void sendMessage() {
        String bodyText = "Bonjour , Vous avez récemment demandé la réinitialisation du mot de passe de votre compte . Cliquez sur le bouton ci-dessous pour continuer. Si vous n'avez pas demandé de réinitialisation de mot de passe, veuillez ignorer cet e-mail ou répondre pour nous en informer.";
        final ProgressDialog dialog = new ProgressDialog(forgot_pass1.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("picalltiservice@gmail.com", "ekvzpeijpsfxfjxg");
                    sender.sendMail("Reset password mail",
                            bodyText,
                            "picalltiservice@gmail.com",
                            mEmail.getText().toString());
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}