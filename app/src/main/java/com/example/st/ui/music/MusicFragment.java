package com.example.st.ui.music;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;


import com.example.st.R;
import com.example.st.databinding.FragmentMusicBinding;

import java.util.ArrayList;

public class MusicFragment extends Fragment {


    private FragmentMusicBinding binding;
    MediaPlayer WhiteNoise1MediaPlayer;
    MediaPlayer WhiteNoise2MediaPlayer;
    MediaPlayer WhiteNoise3MediaPlayer;
    MediaPlayer WhiteNoise4MediaPlayer;
    MediaPlayer WhiteNoise5MediaPlayer;
    MediaPlayer WhiteNoise6MediaPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentMusicBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridView gridView=binding.gridView;
        ArrayList<MusicViewModel> ArrayList = new ArrayList<>();

        GridViewAdapter adapter=new GridViewAdapter(this.requireActivity(),ArrayList);
        gridView.setAdapter(adapter);
        int whiteNoise1= R.raw.whitenoise1;
        int whiteNoise2= R.raw.whitenoise2;
        int whiteNoise3= R.raw.whitenoise3;
        int whiteNoise4= R.raw.whitenoise4;
        int whiteNoise5= R.raw.whitenoise5;
        int whiteNoise6= R.raw.whitenoise6;

         WhiteNoise1MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise1);
        ArrayList.add(new MusicViewModel("Ocean",R.drawable.ic_noise1,WhiteNoise1MediaPlayer));

         WhiteNoise2MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise2);
        ArrayList.add(new MusicViewModel("Forest",R.drawable.ic_noise2,WhiteNoise2MediaPlayer));
         WhiteNoise3MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise3);
        ArrayList.add(new MusicViewModel("Train",R.drawable.ic_noise3,WhiteNoise3MediaPlayer));
        WhiteNoise4MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise4);
        ArrayList.add(new MusicViewModel("Rain",R.drawable.ic_noise4,WhiteNoise4MediaPlayer));
         WhiteNoise5MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise5);
        ArrayList.add(new MusicViewModel("Thunder",R.drawable.ic_noise5,WhiteNoise5MediaPlayer));
        WhiteNoise6MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise6);
        ArrayList.add(new MusicViewModel("Wind",R.drawable.ic_noise6,WhiteNoise6MediaPlayer));

    }



    @Override
    public void onDestroyView() {

        super.onDestroyView();
        binding = null;
        WhiteNoise1MediaPlayer.stop();
        WhiteNoise2MediaPlayer.stop();
        WhiteNoise3MediaPlayer.stop();
        WhiteNoise4MediaPlayer.stop();
        WhiteNoise5MediaPlayer.stop();
        WhiteNoise6MediaPlayer.stop();


    }


}