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
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.ContactName)
    EditText contactName;
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
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container,frag).commit();

        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        contactSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here we are going to inplement intent that containt alrealdy the destination mail and recuperate the infon from the
                //editTexts thes send email dairectly
                Toast.makeText(getApplicationContext(), "Send succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

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
}