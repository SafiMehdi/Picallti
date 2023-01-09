package com.example.picallti;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class WhatYouNeedActivity extends AppCompatActivity {

    private Chip chip4, chip5, chip6, chip7;
    private Button btnApply;
    private ArrayList<String> selectedChipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_you_need);

        /*chip4 = findViewById(R.id.chip4);
        chip5 = findViewById(R.id.chip5);
        chip6 = findViewById(R.id.chip6);
        chip7 = findViewById(R.id.chip7);

        selectedChipData = new ArrayList<>();

        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selectedChipData.add(buttonView.getText().toString());
                }
                else
                {
                    selectedChipData.remove(buttonView.getText().toString());
                }
            }
        };

        chip4.setOnCheckedChangeListener(checkedChangeListener);
        chip5.setOnCheckedChangeListener(checkedChangeListener);
        chip6.setOnCheckedChangeListener(checkedChangeListener);
        chip7.setOnCheckedChangeListener(checkedChangeListener);*/

        /*Spinner spinner = (Spinner) findViewById(R.id.cities_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/
    }
}