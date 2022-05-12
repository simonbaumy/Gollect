package com.example.gollect.ui.recyclebin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecycleBinViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RecycleBinViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}