package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseVerificationWayPassWActivity extends AppCompatActivity {

    @BindView(R.id.Vmail)
    Button Vmail;
    @BindView(R.id.Vphone)
    Button Vphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_verification_way_pass_wactivity);
        ButterKnife.bind(this);

        Vmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), forgot_pass1.class));
            }
        });

        Vphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), forgot_passPhoneActivity.class));
            }
        });
    }
}