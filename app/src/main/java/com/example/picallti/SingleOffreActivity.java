package com.example.picallti;

import static com.example.picallti.login_page.PREFS_NAME;

import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import adapters.CommentsAdapter;
import adapters.NotificationAdapter;
import adapters.OffresAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.Commentaire;
import data.Favoris;
import data.Notification;
import data.Offre;
import data.User;
import data.Vehicule;
import data.VehiculeType;
import retrofit.CommentApi;
import retrofit.FavorisApi;
import retrofit.NotificationApi;
import retrofit.OffreApi;
import retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleOffreActivity extends AppCompatActivity {

    @BindView(R.id.titreOffre)
    TextView titreOffre;
    @BindView(R.id.imageOffre)
    ImageView imageOffre;
    @BindView(R.id.prix)
    TextView prix;
    @BindView(R.id.time)
    TextView time;
    //@BindView(R.id.img_view)
    //ImageView userImage;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.appeler)
    ImageButton appeler;
    @BindView(R.id.whatsapp)
    ImageButton whatsapp;
    @BindView(R.id.share)
    ImageButton share;
    @BindView(R.id.favoris)
    ImageButton like;
    @BindView(R.id.sendComment)
    EditText sendComment;
    int phoneNummber;

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    RetrofitService retrofitService = new RetrofitService();
    CommentApi commentApi = retrofitService.getRetrofit().create(CommentApi.class);

    //The function that implements the sidebar
    public void Sidebar(){
        NavigationView navView = findViewById(R.id.sidebar_view);
        navView.inflateMenu(R.menu.sidebar_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent_profile = new Intent(SingleOffreActivity.this, ProfileActivity.class);
                        startActivity(intent_profile);
                        break;
                    case R.id.nav_likes:
                        Intent intent_likes = new Intent(SingleOffreActivity.this, FavorisActivity.class);
                        startActivity(intent_likes);
                        break;
                    case R.id.nav_langues:
                        Intent intent_langues = new Intent(SingleOffreActivity.this, LanguagesActivity.class);
                        startActivity(intent_langues);
                        break;
                    case R.id.nav_apropos:
                        Intent intent_apropos = new Intent(SingleOffreActivity.this, AproposActivity.class);
                        startActivity(intent_apropos);
                        break;
                    case R.id.nav_parametre:
                        Intent intent_parametre = new Intent(SingleOffreActivity.this, ParametresActivity.class);
                        startActivity(intent_parametre);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_single_offer_page);

        ImageButton toggleButton = findViewById(R.id.sidebar_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open or close the navigation drawer when the button is clicked
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_offre);

        recyclerView = findViewById(R.id.view_holder_comments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        titreOffre.setText(extras.getString("titre"));
        int photo = R.drawable.avatar_2;
        /*if(getResources().getResourceName((int)extras.getDouble("photo") ) != null){
            photo = extras.getInt("photo");
        }*/
        imageOffre.setBackgroundResource(photo);
        prix.setText(Double.toString(extras.getDouble("prix")));
        time.setText(extras.getString("time"));
        description.setText(extras.getString("description"));
        this.phoneNummber = extras.getInt("phone");

        commentApi.getAllCommentairesByOffre(extras.getInt("id"))
                .enqueue(new Callback<Collection<Commentaire>>() {
                    @Override
                    public void onResponse(Call<Collection<Commentaire>> call, Response<Collection<Commentaire>> response) {
                        if ( response.body() != null){
                            System.out.println("working");
                            ArrayList<Commentaire> commentaires =new ArrayList<>();
                            commentaires = new ArrayList<Commentaire>(response.body());
                            System.out.println(commentaires);
                            adapter = new CommentsAdapter(getApplicationContext(), commentaires);
                            recyclerView.setAdapter(adapter);

                            if(commentaires.size() > adapter.getItemCount()){
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Collection<Commentaire>> call, Throwable t) {
                        System.out.println("Exception");
                        Logger.getLogger(SingleOffreActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);

                    }
                });

        //Sidebar implementation
        Sidebar();
    }

    @OnClick(R.id.commentSender)
    public void addComment(){
        String commentSent = sendComment.getText().toString();
        User user = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);
        Bundle extras = getIntent().getExtras();
        Vehicule vehicule = (Vehicule)getIntent().getSerializableExtra("vehicule");

        Offre offre = new Offre(extras.getInt("id"), imageOffre.getId(),extras.getString("titre"),extras.getString("description"),extras.getString("localisation"),extras.getFloat("prix"),extras.getString("time"),extras.getString("operation"),user,vehicule,extras.getString("date"),extras.getString("ville"));
        Commentaire commentaire = new Commentaire(commentSent, user,offre,LocalDate.now().toString(), LocalTime.now().toString());

        commentApi.addComment(commentaire)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(SingleOffreActivity.this, "Comment created !",Toast.LENGTH_SHORT).show();
                        Bundle extras = getIntent().getExtras();
                        User user1 = offre.getUser();
                        user1.setId(extras.getInt("id_user"));

                        Notification notification = new Notification("Your Offre has been commented",commentaire.getCommentaire(),LocalDate.now().toString(),LocalTime.now().toString(),offre.getUser());
                        NotificationApi notificationApi =retrofitService.getRetrofit().create(NotificationApi.class);
                        notificationApi.addNotification(notification).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                System.out.println("notification created");
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                System.out.println("erreur add notification");

                            }
                        });

                        sendComment.setText("");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Logger.getLogger(SingleOffreActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);
                    }
                });
    }


    @OnClick(R.id.appeler)
    public void callOwner(){
        System.out.println(phoneNummber);
        Uri phone = Uri.parse("tel:"+phoneNummber);
        Intent call = new Intent(Intent.ACTION_DIAL,phone);
        startActivity(call);
    }
    @OnClick(R.id.favoris)
    public void addToFav(){
        User user = login_page.getSavedObjectFromPreference(getApplicationContext(),PREFS_NAME,"connectedUser",User.class);
        Bundle extras = getIntent().getExtras();
        Offre offre = new Offre(extras.getInt("id"), imageOffre.getId(),extras.getString("titre"),extras.getString("description"),extras.getString("localisation"),extras.getFloat("prix"),extras.getString("time"),extras.getString("operation"),user,(Vehicule) extras.getSerializable("vehicule"),extras.getString("date"),extras.getString("ville"));

        offre.setId(extras.getInt("id"));
        Favoris favoris = new Favoris(user,offre);
        RetrofitService retrofitService = new RetrofitService();
        FavorisApi favorisApi = retrofitService.getRetrofit().create(FavorisApi.class);
        favorisApi.addFavoris(favoris).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                System.out.println("working");
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Not working");
            }
        });
    }



}
