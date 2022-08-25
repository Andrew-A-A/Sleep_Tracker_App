package com.example.st.ui.account;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.st.AppActivity;
import com.example.st.Database.SleepTrackerDatabase;
import com.example.st.R;
import com.example.st.databinding.FragmentUpdateWakeUpTimeBinding;
import com.example.st.ui.login.LoginViewModel;


public class UpdateWakeUpTimeFragment extends Fragment {
    FragmentUpdateWakeUpTimeBinding binding;
    SleepTrackerDatabase database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentUpdateWakeUpTimeBinding.inflate(inflater,container,false);
        database=new SleepTrackerDatabase(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
//               Toast.makeText(getContext(), binding.updateSleepTimepicker.getMinute(), Toast.LENGTH_SHORT).show();
                boolean update =database.UpdateUserWakingSchedule(LoginViewModel.ViewModel.currentEmail,binding.updateSleepTimepicker.getHour(),
                        binding.updateSleepTimepicker.getMinute());
                if (update){
                    Toast.makeText(getContext(), "updated", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getContext(),"Not updated", Toast.LENGTH_SHORT).show();

                }

                LogData();
                Log.i("zoz",LoginViewModel.ViewModel.currentEmail);
                Intent i = new Intent(getActivity(), AppActivity.class);
                startActivity(i);
            }
        });

    }private String LogData() {
        Cursor cursor=database.ViewCurrentUserData(LoginViewModel.ViewModel.currentEmail);
        //  cursor.moveToFirst();
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append("Email: " + cursor.getString(0) + "\n");
            buffer.append("Username: " + cursor.getString(1) + "\n");
            buffer.append("Password: " + cursor.getString(2) + "\n");
            buffer.append("Sleep Time Hour: " + cursor.getString(3) + "\n");
            buffer.append("Sleeping Time Minute: " + cursor.getInt(4) + "\n");
            buffer.append("Wakeup Time Hour: " + cursor.getInt(5) + "\n");
            buffer.append("Wakeup Time Minute: " + cursor.getInt(6)  + "\n");
            buffer.append("Sign up date: "+ cursor.getInt(7) + "-" + cursor.getInt(8) +"-"+ cursor.getInt(9) + "\n");
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