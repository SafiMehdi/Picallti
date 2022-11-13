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
        //setHasOptionsMenu(true);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = (int) displayMetrics.widthPixels / 4;
        //home.setHeight(30);
        //home.setWidth(w);
        return inflater.inflate(R.layout.bottom_bar_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = (int) displayMetrics.widthPixels / 4;
        //home.setHeight(30);
        //home.setWidth(w);
        ImageButton homee = getView().findViewById(R.id.homee);
        ImageButton profile = getView().findViewById(R.id.profil);
        ImageButton favorits = getView().findViewById(R.id.favoris);
        ImageButton notification = getView().findViewById(R.id.notification);

        Log.d("Yay", "Tesst");
        System.out.println(homee);
        homee.setMinimumWidth(w);
        //homee.setHeight(30);
        //homee.setWidth(w);
        profile.setMinimumWidth(w);
        favorits.setMinimumWidth(w);
        notification.setMinimumWidth(w);
        String currentActivity = getActivity().getLocalClassName();
        System.out.println("---------------------------");
        System.out.println(currentActivity);
        System.out.println("---------------------------------");
        switch (currentActivity) {
            case "MainActivity":
                homee.setColorFilter(Color.rgb(0, 223, 255));
                break;
            case "NotificationsHistory":
                notification.setColorFilter(Color.rgb(0, 223, 255));
                break;

        }

        homee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Homee");
                //profile.setColorFilter(Color.rgb(0, 223, 255));
            }
        });
        favorits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Homee");
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        Log.d("Yay", "Tesst");
        menuInflater.inflate(R.menu.bottom_menu, menu);

        MenuItem pinMenuItem = menu.findItem(R.id.home);
        System.out.println("------------------------------");
        System.out.println(pinMenuItem);
        System.out.println("------------------------------");
        /*pinMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        System.out.println("Heere");
                        return true;
                    case R.id.profil:
                        Intent home = new Intent(getActivity(),MainActivity.class);
                        startActivity(home);

                    default:
                        System.out.println("Heere");
                }
                return false;
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("Heere");
        switch (item.getItemId()) {
            case R.id.home:
                System.out.println("Heere");
                Intent home = new Intent(getActivity(), MainActivity.class);
                startActivity(home);
        }
        return true;
    }

    public void homee() {
        System.out.println("Homee");
    }

}