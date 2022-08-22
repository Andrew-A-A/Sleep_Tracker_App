package com.example.st.ui.music;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.st.R;
import com.example.st.databinding.FragmentMusicBinding;

public class MusicFragment extends Fragment {
    MusicViewModel musicViewModel;

    private FragmentMusicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);

        binding = FragmentMusicBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int whiteNoise1= R.raw.whitenoise1;
        int whiteNoise2= R.raw.whitenoise2;
        MediaPlayer WhiteNoise1MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise1);
       MediaPlayer WhiteNoise2MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise2);
        final boolean[] isPlaying1 = {WhiteNoise1MediaPlayer.isPlaying()};
        final boolean[] isPlaying2 = {WhiteNoise2MediaPlayer.isPlaying()};
       musicViewModel.isPlaying1 =isPlaying1;
       musicViewModel.isPlaying2 =isPlaying2;
        binding.offlineSound1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicViewModel.isPlaying1[0]) {
                    binding.offlineSound1Button.setText("▶");
                    WhiteNoise1MediaPlayer.setLooping(true);
                    WhiteNoise1MediaPlayer.pause();
                }
                else {
                    binding.offlineSound1Button.setText("■");
                    WhiteNoise1MediaPlayer.start();
                }
                isPlaying1[0] =(!isPlaying1[0]);
            }
        }); binding.offlineSound2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicViewModel.isPlaying2[0]) {
                    binding.offlineSound2Button.setText("▶");
                    WhiteNoise2MediaPlayer.setLooping(true);
                    WhiteNoise2MediaPlayer.pause();
                }
                else {
                    binding.offlineSound2Button.setText("■");
                    WhiteNoise2MediaPlayer.start();
                }
                isPlaying2[0] =(!isPlaying2[0]);
            }
        });

    }



    @Override
    public void onDestroyView() {

        super.onDestroyView();
        binding = null;

    }
}