package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ParametresCompte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_compte);

        /*TextView deleteAccount = (TextView)findViewById(R.id.deleteAccount);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParametresCompte.this, DeleteAccount.class));
            }
        });

        TextView forgottenmdp = (TextView)findViewById(R.id.mdpForgotten);
        forgottenmdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParametresCompte.this, forgot_pass2.class));
            }
        });

        TextView disconnect = (TextView)findViewById(R.id.disconnect);
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( ParametresCompte.this, "You have been disconnected!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ParametresCompte.this, login_page.class));
            }
        });*/


    }
}