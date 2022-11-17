package com.example.sidebar.ui.apparence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ApparenceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ApparenceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Apparence fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}