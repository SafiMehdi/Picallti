package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DeleteAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        Button supprCompte = (Button)findViewById(R.id.deleteAccountButton);
        supprCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( DeleteAccount.this, "Your Account has been Deleted !",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeleteAccount.this, login_page.class));
            }
        });
        ImageView back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteAccount.this, login_page.class));

            }
        });
    }
}