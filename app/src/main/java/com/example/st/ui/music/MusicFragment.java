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
import android.widget.Toast;

import com.example.st.R;
import com.example.st.databinding.FragmentMusicBinding;

public class MusicFragment extends Fragment {
    MusicViewModel musicViewModel;

    private FragmentMusicBinding binding;
    MediaPlayer WhiteNoise1MediaPlayer;
    MediaPlayer WhiteNoise2MediaPlayer;
    MediaPlayer WhiteNoise3MediaPlayer;
    MediaPlayer WhiteNoise4MediaPlayer;
    MediaPlayer WhiteNoise5MediaPlayer;
    MediaPlayer WhiteNoise6MediaPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        musicViewModel =
                new ViewModelProvider(requireActivity()).get(MusicViewModel.class);

        binding = FragmentMusicBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int whiteNoise1= R.raw.whitenoise1;
        int whiteNoise2= R.raw.whitenoise2;
        int whiteNoise3= R.raw.whitenoise3;
        int whiteNoise4= R.raw.whitenoise4;
        int whiteNoise5= R.raw.whitenoise5;
        int whiteNoise6= R.raw.whitenoise6;

         WhiteNoise1MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise1);
         WhiteNoise2MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise2);
         WhiteNoise3MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise3);
         WhiteNoise2MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise4);
         WhiteNoise2MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise5);
         WhiteNoise2MediaPlayer= MediaPlayer.create(getActivity(), whiteNoise6);
        binding.offlineSound1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WhiteNoise1MediaPlayer.isPlaying()) {
                    binding.offlineSound1Button.setText("▶");
                    WhiteNoise1MediaPlayer.setLooping(true);
                    WhiteNoise1MediaPlayer.pause();
                }
                else {
                    binding.offlineSound1Button.setText("■");
                    WhiteNoise1MediaPlayer.start();
                }

            }
        }); binding.offlineSound2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WhiteNoise2MediaPlayer.isPlaying()) {
                    binding.offlineSound2Button.setText("▶");
                    WhiteNoise2MediaPlayer.setLooping(true);
                    WhiteNoise2MediaPlayer.pause();
                }
                else {
                    binding.offlineSound2Button.setText("■");
                    WhiteNoise2MediaPlayer.start();
                }

            }
        });

    }



    @Override
    public void onDestroyView() {

        super.onDestroyView();
        binding = null;
        WhiteNoise1MediaPlayer.stop();
        WhiteNoise2MediaPlayer.stop();

    }
}