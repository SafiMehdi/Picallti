package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParametresActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.deleteAccountSection)
    public void deleteaccountClicked(){
        Intent intent = new Intent(this,DeleteAccount.class);
        startActivity(intent);
    }

    @OnClick(R.id.changePasswordSection)
    public void changePasswordClicked(){
        Intent intent = new Intent(this,forgot_pass1.class);
        startActivity(intent);
    }

    @OnClick(R.id.contactUsSection)
    public void contactUsClicked(){
        Intent intent = new Intent(this,ContactActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.logOutSection)
    public void logOutClicked(){
        Toast.makeText( ParametresActivity.this, "You've been disconnected!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ParametresActivity.this, login_page.class));
    }

}