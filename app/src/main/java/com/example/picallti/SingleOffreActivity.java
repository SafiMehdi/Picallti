package com.example.picallti;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import static com.example.picallti.login_page.PREFS_NAME;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import androidx.core.app.NotificationManagerCompat;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

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
import data.*;
import retrofit.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleOffreActivity extends AppCompatActivity {

    final String PICALLTI_CHANNEL_ID = "PICALLTI_CHANNEL_ID";

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

    Boolean isFav;

    ArrayList<Commentaire> commentaires = new ArrayList<>();
    NotificationManagerCompat notificationManagerCompat;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    RetrofitService retrofitService = new RetrofitService();
    CommentApi commentApi = retrofitService.getRetrofit().create(CommentApi.class);

    BottomBarFragment frag = new BottomBarFragment();

    //The function that implements the sidebar
    public void Sidebar() {
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
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_bar_container, frag).commit();

        createNotificationChannel();

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
        prix.setText(Float.toString(extras.getFloat("prix")));
        time.setText(extras.getString("time"));
        description.setText(extras.getString("description"));
        this.phoneNummber = extras.getInt("phone");

        commentApi.getAllCommentairesByOffre(extras.getInt("id"))
                .enqueue(new Callback<Collection<Commentaire>>() {
                    @Override
                    public void onResponse(Call<Collection<Commentaire>> call, Response<Collection<Commentaire>> response) {
                        if ( response.body() != null){
                            System.out.println("working");
                            commentaires = new ArrayList<Commentaire>(response.body());
                            System.out.println(commentaires);
                            adapter = new CommentsAdapter(getApplicationContext(), commentaires);
                            recyclerView.setAdapter(adapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<Collection<Commentaire>> call, Throwable t) {
                        System.out.println("Exception");
                        Logger.getLogger(SingleOffreActivity.class.getName()).log(Level.SEVERE, "Error Occured", t);

                    }
                });

        notificationManagerCompat = NotificationManagerCompat.from(SingleOffreActivity.this);

        //Sidebar implementation
        Sidebar();
        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(), PREFS_NAME, "connectedUser", User.class);
        //User user = new User(2,"nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");
        Offre offre = new Offre();
        offre.setId(extras.getInt("id"));
        Favoris favoris = new Favoris(connectedUser, offre);
        RetrofitService retrofitService1 = new RetrofitService();
        FavorisApi favorisApi = retrofitService1.getRetrofit().create(FavorisApi.class);
        favorisApi.checkIfExists(favoris).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                //System.out.println("response.body()");
                isFav = response.body();
                if (!isFav) {
                    like.setBackgroundResource(R.drawable.like);
                } else {
                    like.setBackgroundResource(R.drawable.redheart);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println("can't know if exists");
            }
        });
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
                        //user1.setId(extras.getInt("id_user"));
                        System.out.println("the id is : " + user1.getId());
                        commentaires.add(commentaire);
                        adapter.notifyDataSetChanged();

                        data.Notification notification = new data.Notification(user1.getNom()+" has commented",commentaire.getCommentaire(),LocalDate.now().toString(),LocalTime.now().toString(),offre.getUser());
                        System.out.println(" the ids offer is : "+ offre.toString());
                        NotificationApi notificationApi =retrofitService.getRetrofit().create(NotificationApi.class);
                        notificationApi.addNotification(notification).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                System.out.println("notification created");
                                createSimpleNotification(commentaire.getUser().getNom()+" has commented on your post","picallti",1);
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



    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.appeler)
    public void callOwner() {
        System.out.println(phoneNummber);
        Uri phone = Uri.parse("tel:" + phoneNummber);
        Intent call = new Intent(Intent.ACTION_DIAL, phone);
        startActivity(call);
    }

    @OnClick(R.id.favoris)
    public void addToFav() {
        User connectedUser = login_page.getSavedObjectFromPreference(getApplicationContext(), PREFS_NAME, "connectedUser", User.class);
        //User user = new User(2,"nom","prenom","M","testttt@test.com",78,"pass",78,"bio","admin");
        Bundle extras = getIntent().getExtras();
        Offre offre = new Offre();
        offre.setId(extras.getInt("id"));
        Favoris favoris = new Favoris(connectedUser, offre);
        RetrofitService retrofitService = new RetrofitService();
        FavorisApi favorisApi = retrofitService.getRetrofit().create(FavorisApi.class);
        if (!isFav) {
            isFav = true;
            favorisApi.addFavoris(favoris).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    System.out.println("working");
                    System.out.println(response);
                    like.setBackgroundResource(R.drawable.redheart);

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println("Not working");
                }
            });
        } else {
            isFav = false;
            favorisApi.remove(favoris).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    System.out.println("remove");
                    like.setBackgroundResource(R.drawable.like);

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println("not removing");
                }
            });

        }

    }


    private void createSimpleNotification(String title, String text, int notificationId) {

        notificationManagerCompat.cancelAll();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.notifications_icon);

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NotificationsHistory.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);


        Notification notification = new NotificationCompat.Builder(this, PICALLTI_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(commentaires.get(commentaires.size()-1).getCommentaire()))
                .build();

        notificationManagerCompat.notify(notificationId, notification);
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "PICALLTI_CHANNEL";
            String description = "picallti channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(PICALLTI_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

/*

 */