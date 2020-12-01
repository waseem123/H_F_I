package com.infostackresearch.homefit.ui.bmi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BmiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BmiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("BMI Calculation");
    }

    public LiveData<String> getText() {
        return mText;
    }
}