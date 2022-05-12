package com.example.gollect.ui.Favourites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavouritesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FavouritesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}