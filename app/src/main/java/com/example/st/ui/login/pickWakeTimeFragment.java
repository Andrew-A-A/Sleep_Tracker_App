package com.example.st.ui.login;


import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.st.AppActivity;
import com.example.st.Database.SleepTrackerDatabase;

import com.example.st.databinding.FragmentPickWakeTimeBinding;


public class pickWakeTimeFragment extends Fragment {
    FragmentPickWakeTimeBinding binding;
    SleepTrackerDatabase database;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentPickWakeTimeBinding.inflate(inflater,container,false);
        database=new SleepTrackerDatabase(getActivity());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                LoginViewModel.ViewModel.wakeHour=binding.sleepTimepicker.getHour();
                LoginViewModel.ViewModel.wakeMinute=binding.sleepTimepicker.getMinute();
                String email=LoginViewModel.ViewModel.Email;
                String name=LoginViewModel.ViewModel.Name;
                String password=LoginViewModel.ViewModel.Password;
                int sleepHour=LoginViewModel.ViewModel.sleepHour;
                int sleepMinute=LoginViewModel.ViewModel.sleepMinute;
                int wakeMinute= LoginViewModel.ViewModel.wakeMinute;
                int wakeHour= LoginViewModel.ViewModel.wakeHour;
                database.InsertUser(email,name,password,sleepHour,sleepMinute,wakeHour,wakeMinute);
                Toast.makeText(getContext(),"User Added !",Toast.LENGTH_SHORT).show();
              //  LogData();
                Intent i = new Intent(getActivity(), AppActivity.class);
                startActivity(i);

            }
        });
    }

//    private String LogData() {
//        Cursor cursor = database.ViewUserData();
//        StringBuffer buffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//            buffer.append("Email: " + cursor.getString(0) + "\n");
//            buffer.append("Username: " + cursor.getString(1) + "\n");
//            buffer.append("Password: " + cursor.getString(2) + "\n");
//            buffer.append("Sleep Time Hour: " + cursor.getString(3) + "\n");
//            buffer.append("Sleeping Time Minute: " + cursor.getInt(4) + "\n");
//            buffer.append("Wakeup Time Hour: " + cursor.getInt(5) + "\n");
//            buffer.append("Wakeup Time Minute: " + cursor.getInt(6)  + "\n");
//            buffer.append("Sign up date: "+ cursor.getInt(7) + "-" + cursor.getInt(8) +"-"+ cursor.getInt(9) + "\n");
//            buffer.append("----------------------------" + "\n");
//
//        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setCancelable(true);
//        builder.setTitle("User Entry Details");
//        builder.setMessage(buffer.toString());
//        builder.show();
//
//        return buffer.toString();
//    }

}