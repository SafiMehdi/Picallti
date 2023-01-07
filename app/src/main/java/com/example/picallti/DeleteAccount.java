package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import data.User;
import retrofit.RetrofitService;
import retrofit.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAccount extends AppCompatActivity {

    public static String PREFS_NAME = "myFile";

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        Button supprCompte = (Button)findViewById(R.id.deleteAccountButton);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        User connectedUser = getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME, "connectedUser", User.class);


        supprCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(connectedUser != null){
                    Integer id = connectedUser.getId();
                    userApi.removeUser(id).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(DeleteAccount.this,"L'utilisateur a bien été supprimé !",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.e("Opération échouée",t.getMessage());

                            }
                        });
                        Intent intent = new Intent(DeleteAccount.this, login_page.class);
                        startActivity(intent);

                    }
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