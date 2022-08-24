package com.example.st.ui.music;

import android.media.MediaPlayer;

import androidx.lifecycle.ViewModel;

public class MusicViewModel extends ViewModel {
    String noiseName;
    int imgid;
    MediaPlayer mediaPlayer;

    public MusicViewModel(String noiseName, int imgid, MediaPlayer mediaPlayer) {
            this.noiseName = noiseName;
            this.imgid = imgid;
            this.mediaPlayer=mediaPlayer;
        }


//        mText=new MutableLiveData<>();


//        mText.setValue("This is Music fragment");
    }
//    public LiveData<String> getText(){
//        return mText;
//    }

