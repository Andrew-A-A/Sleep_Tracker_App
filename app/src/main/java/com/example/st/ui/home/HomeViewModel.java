package com.example.st.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.st.databinding.FragmentHomeBinding;

import nl.joery.timerangepicker.TimeRangePicker;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}