package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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
}