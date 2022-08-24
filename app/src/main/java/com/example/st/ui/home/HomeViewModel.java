package com.example.st.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



import nl.joery.timerangepicker.TimeRangePicker;

public class HomeViewModel extends ViewModel {

 //variables that holds start time,end time and duration in case we needed it for any backend calculation
     TimeRangePicker.Time startTime,endTime;
    TimeRangePicker.TimeDuration duration;


    private final MutableLiveData<String> mText;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}