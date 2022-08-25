package com.example.st.ui.home;
import android.annotation.SuppressLint;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.AlarmClock;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.st.Database.SleepTrackerDatabase;
import com.example.st.databinding.FragmentHomeBinding;
import com.example.st.ui.login.LoginViewModel;


import nl.joery.timerangepicker.TimeRangePicker;

public class HomeFragment extends Fragment {
//Declare Binding Variable that will hold IDs of all Views in fragment instead of using "findViewById()" function
    private FragmentHomeBinding binding;
    SleepTrackerDatabase database;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    //First function called to create the fragment
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        database=new SleepTrackerDatabase(getActivity());
//Declare Object of the viewModel that holds all backend functions and variables
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

//Assign value to binding variable to hold the IDs
        binding = FragmentHomeBinding.inflate(inflater, container, false);
//Set the Default values of the time picker to the Text view
        Cursor cursor=database.ViewCurrentUserData(LoginViewModel.ViewModel.currentEmail);
        cursor.moveToFirst();
        int wakemin=(cursor.getInt(6));
        int wakehour=cursor.getInt(5);
        int sleephour=cursor.getInt(3);
        int sleepmin=cursor.getInt(4);
        binding.picker.setStartTime(new TimeRangePicker.Time(sleephour,sleepmin));
        int totalmin=(wakehour*60)+wakemin;
        binding.picker.setEndTimeMinutes(totalmin);

        binding.startTimeTextView.setText(binding.picker.getStartTime().toString());
        binding.endTimeTextView.setText(binding.picker.getEndTime().toString());
        binding.durationTextView.setText(binding.picker.getDuration().toString());



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

        binding.SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration=database.getDuration(binding.picker.getDuration().getHour(),binding.picker.getDuration().getMinute());
                float cycles=database.GetCycles(duration);
                String rate= database.GetRating(cycles);
                int day= database.NumDays(LoginViewModel.ViewModel.currentEmail);
                database.InsertSleepingSchedule(binding.picker.getStartTime().getHour(),binding.picker.getStartTime().getMinute(),binding.picker.getEndTime().getHour(),
                        binding.picker.getEndTime().getMinute(),duration,cycles,rate, LoginViewModel.ViewModel.currentEmail,day);
                Cursor current= database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
                Log.i("Data",LogData());
                int currentHour;
                currentHour =binding.picker.getEndTime().getHour();
                int currentMinute;
                currentMinute = binding.picker.getEndTime().getMinute();
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR,currentHour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,currentMinute);
                if(currentHour<=24 && currentMinute <=60){
                    startActivity(intent);}

            }
        });
        return binding.getRoot();
    }

    private String LogData() {
        Cursor cursor = database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append("RecordID: " + cursor.getInt(0) + "\n");
            buffer.append("Sleeping Hour: " + cursor.getInt(1) + "\n");
            buffer.append("Sleeping Minute: " + cursor.getInt(2) + "\n");
            buffer.append("Wake Up Hour: " + cursor.getInt(3) + "\n");
            buffer.append("Wake Up Minute: " + cursor.getInt(4) + "\n");
            buffer.append("Duration: " + cursor.getInt(5) + "\n");
            buffer.append("Number of Cycles: " + cursor.getFloat(6)  + "\n");
            buffer.append("Rating: "+ cursor.getString(7)  + "\n");
            buffer.append("Email: "+ cursor.getString(8)  + "\n");
            buffer.append("Day: "+ cursor.getInt(9)  + "\n");
            buffer.append("----------------------------" + "\n");

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle("User Entry Details");
        builder.setMessage(buffer.toString());
       // builder.show();

        return buffer.toString();
    }
}