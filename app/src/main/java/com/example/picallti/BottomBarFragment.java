package com.example.picallti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class BottomBarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.bottom_bar_fragment, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        Log.d("Yay","Tesst");
        menuInflater.inflate(R.menu.bottom_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);

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
        }
        return true;
    }
}