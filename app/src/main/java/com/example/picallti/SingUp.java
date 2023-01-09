package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import MailAPI2.GMailSender;
import data.User;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SingUp extends AppCompatActivity {
    EditText surname, name, email, emailVerif, phoneNumber, password, passwordVerif;
    private Button signup;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingUp.this, login_page.class));
            }
        });
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        emailVerif = findViewById(R.id.emailVerif);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        passwordVerif = findViewById(R.id.passwordVerif);
        signup = (Button) findViewById(R.id.createAccountButton);
        CheckBox terms = (CheckBox) findViewById(R.id.conditions);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!validateSurname() | !validateName() | !validatePhoneNo() | !validateEmail()
                        | !validateEmailValidation() | !validatePassword() | !validatePasswordValidation()) {
                    return;
                } else if (!terms.isChecked()) {
                    Toast.makeText(SingUp.this, "You should accept our terms and conditions !", Toast.LENGTH_SHORT).show();
                } else {
                    String nom = surname.getText().toString();
                    String prenom = name.getText().toString();
                    String mail = email.getText().toString();
                    String mdp = password.getText().toString();
                    int phone = Integer.parseInt(phoneNumber.getText().toString());
                    int photo = R.drawable.avatar_2;
                    User user = new User(nom, prenom, mail, phone, mdp, photo);

                    userApi.addUser(user)
                            .enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    if(response.isSuccessful()){
                                        startActivity(new Intent(SingUp.this, login_page.class));
                                        Toast.makeText(SingUp.this, "Account Created !", Toast.LENGTH_SHORT).show();
                                    }else if(response.code() == 400){
                                        email.setError("Email already exists !!");
                                        Toast.makeText(SingUp.this, "Email already exists !!", Toast.LENGTH_SHORT).show();

                                    }else if (response.code() == 406){
                                        phoneNumber.setError("phone number already exists !!");
                                        Toast.makeText(SingUp.this, "phone number already exists !!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Logger.getLogger(SingUp.class.getName()).log(Level.SEVERE, "Error Occured", t);
                                }
                            });
                }
            }
        });

       /* Spinner spinner = (Spinner) findViewById(R.id.Ville);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/

    }

    private void sendMessage() {
        String bodyText = "Mail de creation de compte , TEST";
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("picalltiservice@gmail.com", "ekvzpeijpsfxfjxg");
                    sender.sendMail("Welcome to Picallti APP",
                            bodyText,
                            "picalltiservice@gmail.com",
                            email.getText().toString());

                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());

                }
            }
        });
        sender.start();
    }

    private Boolean validateSurname() {
        String val = surname.getText().toString();
        String validName = "(?=^[^0-9]+$)";
        if (val.isEmpty()) {
            surname.setError("Field cannot be empty");
            return false;
        } else if (val.matches(validName)) {
            surname.setError("Surname should not contain digits !");
            return false;
        } else {
            surname.setError(null);
            return true;
        }
    }

    private Boolean validateName() {
        String val = name.getText().toString();
        String validName = "(?=^[^0-9]+$)";
        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else if (val.matches(validName)) {
            name.setError("Name should not contain digits !");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getText().toString();
        String validEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field cannot be empty !");
            return false;
        } else if (!val.matches(validEmail)) {
            email.setError("Invalid email address !");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private Boolean validateEmailValidation() {
        String val = emailVerif.getText().toString();
        String val2 = email.getText().toString();
        if (val.isEmpty()) {
            emailVerif.setError("Field cannot be empty !");
            return false;
        } else if (!val.equals(val2)) {
            emailVerif.setError("Email doesn't match !");
            return false;
        } else {
            emailVerif.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = phoneNumber.getText().toString();
        String val2 = val.length() < 3 ? "00" : val.substring(0, 2);
        //System.out.println(val2);
        if (val.isEmpty()) {
            phoneNumber.setError("Field cannot be empty !");
            return false;
        } else if (val.length() != 10) {
            phoneNumber.setError("Only 10 digits are allowed !");
            return false;
        } else if (!val2.equals("06") && !val2.equals("07")) {
            phoneNumber.setError("Phone number should start with 06 or 07 !");
            return false;
        } else {
            phoneNumber.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getText().toString();
        String strongPassword = "^" +
                "(?=.*[0-9])" + // at least 1 digit
                "(?=.*[a-z])" + // at least 1 lower case letter
                "(?=.*[A-Z])" + // at least 1 Upper case letter
                "(?=.*[a-zA-Z])" + // Any letter
                "(?=.*[@#$%^&+=])" + // At least 1 special character
                "(?=\\S+$)" + // No whitespaces
                ".{4,}" + // At least 4 characters
                "$";
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(strongPassword)) {
            password.setError("The password is too weak !\nAt least 4 characters containing 1 digit,1 lower case letter, 1 Upper case letter,1 Special character ");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private Boolean validatePasswordValidation() {
        String val = passwordVerif.getText().toString();
        String val2 = password.getText().toString();
        if (val.isEmpty()) {
            passwordVerif.setError("Field cannot be empty !");
            return false;
        } else if (!val.equals(val2)) {
            passwordVerif.setError("Password doesn't match !");
            return false;
        } else {
            passwordVerif.setError(null);
            return true;
        }
    }




}