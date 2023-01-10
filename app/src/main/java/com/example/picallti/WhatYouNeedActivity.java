package com.example.picallti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WhatYouNeedActivity extends AppCompatActivity {

    private Chip chip4, chip5, chip6, chip7;
    private Button btnApply;
    private ArrayList<String> selectedChipData;
    @BindView(R.id.priceMaxTxt)
    TextView maxPrice;
    @BindView(R.id.priceMinTxt)
    TextView minPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_you_need);

        ButterKnife.bind(this);

        maxPrice.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    System.out.println("testtttttttttttttttttt");
                    System.out.println(maxPrice.getText());
                    System.out.println(minPrice.getText());
                    float min = Float.parseFloat(minPrice.getText().toString());
                    float max = Float.parseFloat(maxPrice.getText().toString());
                    if (min > max){
                        Toast toast = Toast.makeText(getApplicationContext(),"Max Value Should be Greater Than The Min",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        Intent intent = new Intent(WhatYouNeedActivity.this, OffrePageActivity.class);
                        intent.putExtra("type","price");
                        intent.putExtra("min",min);
                        intent.putExtra("max",max);
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.Operation);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelected(false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position != 0){
                    Intent intent = new Intent(WhatYouNeedActivity.this, OffrePageActivity.class);
                    intent.putExtra("type","city");
                    intent.putExtra("ville", spinner.getItemAtPosition(position).toString());
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
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
    }
    @OnClick(R.id.velo)
    public void findVelo(){
        Intent intent = new Intent(WhatYouNeedActivity.this, OffrePageActivity.class);
        intent.putExtra("type","vehiculeType");
        intent.putExtra("value","Vélo");
        startActivity(intent);
    }
    @OnClick(R.id.veloElectric)
    public void findElectrique(){
        Intent intent = new Intent(WhatYouNeedActivity.this, OffrePageActivity.class);
        intent.putExtra("type","vehiculeType");
        intent.putExtra("value","Vélo_electrique");
        startActivity(intent);
    }
    @OnClick(R.id.motorcycle)
    public void findMoto(){
        Intent intent = new Intent(WhatYouNeedActivity.this, OffrePageActivity.class);
        intent.putExtra("type","vehiculeType");
        intent.putExtra("value","Moto");
        startActivity(intent);
    }
    @OnClick(R.id.scooter)
    public void findScooter(){
        Intent intent = new Intent(WhatYouNeedActivity.this, OffrePageActivity.class);
        intent.putExtra("type","vehiculeType");
        intent.putExtra("value","Scooter");
        startActivity(intent);
    }
    @OnClick(R.id.plusRecentButton)
    public void findPlusRecent(){
        Intent intent = new Intent(WhatYouNeedActivity.this, OffrePageActivity.class);
        intent.putExtra("type","date");
        intent.putExtra("value","recent");
        startActivity(intent);
    }
    @OnClick(R.id.plusAncienButton)
    public void findPlusAncien(){
        Intent intent = new Intent(WhatYouNeedActivity.this, OffrePageActivity.class);
        startActivity(intent);
    }




}