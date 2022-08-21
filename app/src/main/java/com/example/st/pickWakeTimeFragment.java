package com.example.st;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.st.databinding.FragmentPickWakeTimeBinding;


public class pickWakeTimeFragment extends Fragment {
    FragmentPickWakeTimeBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentPickWakeTimeBinding.inflate(inflater,container,false);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pick_wake_time, container, false);
    }

}