package com.example.sidebar.ui.apropos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AproposViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AproposViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Apropos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}