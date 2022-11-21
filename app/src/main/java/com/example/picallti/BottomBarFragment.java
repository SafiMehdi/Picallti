package com.example.picallti;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BottomBarFragment extends Fragment {

    @BindView(R.id.homee)
    Button home;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_bar_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = (int)( displayMetrics.widthPixels /4);
        //home.setHeight(30);
        //home.setWidth(w);
        ImageButton homee = getView().findViewById(R.id.homee);
        ImageButton profile = getView().findViewById(R.id.profil);
        ImageButton favorits = getView().findViewById(R.id.favoris);
        ImageButton notification = getView().findViewById(R.id.notification);

        Log.d("Yay", "Tesst");
        System.out.println(homee);
        homee.setMinimumWidth(w);
        profile.setMinimumWidth(w);
        favorits.setMinimumWidth(w);
        notification.setMinimumWidth(w);
        String currentActivity = getActivity().getLocalClassName();
        System.out.println("---------------------------");
        System.out.println(currentActivity);
        System.out.println("---------------------------------");
        switch (currentActivity) {
            case "OffrePageActivity":
                homee.setColorFilter(Color.rgb(1, 30, 254));
                break;
            case "Profile":
                profile.setColorFilter(Color.rgb(1, 30, 254));
                break;
            case "NotificationsHistory":
                notification.setColorFilter(Color.rgb(1, 30, 254));
                break;
            case "FavorisActivity":
                favorits.setColorFilter(Color.rgb(1, 30, 254));
                break;


        }

        homee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OffrePageActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Homee");
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
                //profile.setColorFilter(Color.rgb(0, 223, 255));
            }
        });
        favorits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Homee");
                Intent intent = new Intent(getActivity(), FavorisActivity.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationsHistory.class);
                startActivity(intent);
            }
        });

    }


}