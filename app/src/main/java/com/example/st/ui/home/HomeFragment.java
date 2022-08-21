package com.example.st.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.st.databinding.FragmentHomeBinding;

import nl.joery.timerangepicker.TimeRangePicker;

public class HomeFragment extends Fragment {
//Declare Binding Variable that will hold IDs of all Views in fragment instead of using "findViewById()" function
    private FragmentHomeBinding binding;

//First function called to create the fragment
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//Declare Object of the viewModel that holds all backend functions and variables
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

//Assign value to binding variable to hold the IDs
        binding = FragmentHomeBinding.inflate(inflater, container, false);
//Set the Default values of the time picker to the Text view
        binding.startTimeTextView.setText(binding.picker.getStartTime().toString());
        binding.endTimeTextView.setText(binding.picker.getEndTime().toString());
        binding.durationTextView.setText(binding.picker.getDuration().toString());


        int duration=binding.picker.getDuration().getHour();



        Log.i("Time : ", String.valueOf(duration));




//Set the Time Change Click listener to change values of time every time user pick different hour
        binding.picker.setOnTimeChangeListener(new TimeRangePicker.OnTimeChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            //Called only when User change the Start time
            public void onStartTimeChange(@NonNull TimeRangePicker.Time startTime) {
                //set Start time text view
                binding.startTimeTextView.setText(startTime.toString());
                //set Start time variable in the viewModel
                homeViewModel.startTime=startTime;
            }


            @SuppressLint("SetTextI18n")
            @Override
            //Called only when User change the End time
            public void onEndTimeChange(@NonNull TimeRangePicker.Time endTime) {
                //set End time text view
                binding.endTimeTextView.setText(endTime.toString());
                //set End time variable in the viewModel
                homeViewModel.endTime=endTime;
            }

            @SuppressLint("SetTextI18n")
            @Override
            //Called when either Start time or End time change
            public void onDurationChange(@NonNull TimeRangePicker.TimeDuration timeDuration) {
                //set Duration text view
               binding.durationTextView.setText(timeDuration.toString());
                //set Duration variable in the viewModel
               homeViewModel.duration=timeDuration;
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}