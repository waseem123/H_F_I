package com.infostackresearch.homefit.ui.diet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DietViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DietViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Diet plan not present at this moment.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}