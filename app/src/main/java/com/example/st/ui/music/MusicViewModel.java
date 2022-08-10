package com.example.st.ui.music;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MusicViewModel extends ViewModel {
    private final MutableLiveData<String>mText;
    public MusicViewModel(){
        mText=new MutableLiveData<>();
        mText.setValue("This is Music fragment");
    }
    public LiveData<String> getText(){
        return mText;
    }
}
