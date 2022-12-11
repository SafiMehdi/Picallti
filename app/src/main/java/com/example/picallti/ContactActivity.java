package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.ContactSub)
    EditText ContactSub;
    @BindView(R.id.ContactMail)
    EditText contactMail;
    @BindView(R.id.ContactMsg)
    EditText contactMsg;
    @BindView(R.id.ContactSendBtn)
    Button contactSendBtn;
    @BindView(R.id.ContactBack)
    ImageButton contactBack ;

    BottomBarFragment frag = new BottomBarFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();

        contactSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateEmail()){
                    sendMail();
                }else {
                    return;
                }
            }
        });

        contactBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        ConstraintLayout contact = findViewById(R.id.contact);
        ViewGroup.LayoutParams params = contact.getLayoutParams();
        params.height =(int) (getResources().getDisplayMetrics().heightPixels-getResources().getDisplayMetrics().heightPixels/9) ;
        contact.setLayoutParams(params);
    }

    private void sendMail() {
        String recipientList = contactMail.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = ContactSub.getText().toString();
        String message = contactMsg.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
    private Boolean validateEmail(){
        String val = contactMail.getText().toString();
        String validEmail ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            contactMail.setError("Field cannot be empty !");
            return false;
        }else if (!val.matches(validEmail)){
            contactMail.setError("Invalid email address !");
            return false;
        }else {
            contactMail.setError(null);
            return true;
        }
    }
}