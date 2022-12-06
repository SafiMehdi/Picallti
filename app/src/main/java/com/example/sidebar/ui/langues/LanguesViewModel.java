package com.example.sidebar.ui.langues;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LanguesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LanguesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is languages fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Languages part
    //final String [] Languages = {"Français", "English", "العربية"};


}