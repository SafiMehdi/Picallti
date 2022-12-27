package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OTPActivity extends AppCompatActivity {

    @BindView(R.id.phoneNumber)
    EditText phoneNumber;
    @BindView(R.id.verificationCode)
    EditText verificationCode;
    /*@BindView(R.id.verifyCode)
    Button verifyCode;*/
    @BindView(R.id.sendCode)
    Button sendCode;
    @BindView(R.id.txtbacklogin)
    TextView txtbacklogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        ButterKnife.bind(this);

        txtbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OTPActivity.this, login_page.class));
            }
        });

        if(!validatePhoneNo()){
            return;
        }else{
            validatePhoneNo();
            requestSMSPermission();
            new OTP_Receiver().setEditText(verificationCode);
        }
    }


    private Boolean validatePhoneNo(){
        String val = phoneNumber.getText().toString();
        //String val2 = val.substring(0,2);
        //System.out.println(val2);
        if(val.isEmpty()){
            phoneNumber.setError("Field cannot be empty !");
            return false;
        }else if (val.length() != 10 ){
            phoneNumber.setError("Only 10 digits are allowed !");
            return false;
        }/*else if (!val2.equals("06") && !val2.equals("07")){
            phoneNumber.setError("Phone number should start with 06 or 07 !");
            return false;
        }*/
        else {
            phoneNumber.setError(null);
            return true;
        }
    }

    private void requestSMSPermission()
    {
        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED)
        {
            String[] permission_list = new String[1];
            permission_list[0] = permission;

            ActivityCompat.requestPermissions(this, permission_list,1);
        }
    }
}