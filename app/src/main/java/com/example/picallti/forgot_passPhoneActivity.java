package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class forgot_passPhoneActivity extends AppCompatActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.sendCode)
    Button sendCodeBtn;
    @BindView(R.id.verificationCode)
    EditText verificationCode;
    @BindView(R.id.verifyCode)
    Button verifyCodeBtn;
    @BindView(R.id.txtbacklogin)
    TextView txtbacklogin;
    int randomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_phone);
        ButterKnife.bind(this);

        sendCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Construct data
                    String apiKey = "apikey=" + "NTkzNjY3NGM1NzZiNTU3OTcwNDIzNzQ3NTE2YjMwNmQ=";
                    Random random= new Random();
                    randomNumber= random.nextInt(999999);
                    String message = "&message=" + "Your verification code is : " + randomNumber;
                    String sender = "&sender=" + "Picalti";
                    String numbers = "&numbers=" +phone.getText().toString();

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();
                    Toast.makeText(forgot_passPhoneActivity.this, "SEND", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(forgot_passPhoneActivity.this, "Retry Please!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        verifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(randomNumber == Integer.valueOf(verificationCode.getText().toString())){
                    startActivity(new Intent(getApplicationContext(), forgot_pass1.class));
                    Toast.makeText(forgot_passPhoneActivity.this, "Code Verified", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(forgot_passPhoneActivity.this, "Wrong code !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login_page.class));
            }
        });
    }
}