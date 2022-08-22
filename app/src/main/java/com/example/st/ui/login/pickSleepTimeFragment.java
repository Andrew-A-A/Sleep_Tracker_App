package com.example.st.ui.login;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.st.R;
import com.example.st.databinding.FragmentPickSleepTimeBinding;


public class pickSleepTimeFragment extends Fragment {

    FragmentPickSleepTimeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentPickSleepTimeBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.sleepTimeNextButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                LoginViewModel.ViewModel.sleepHour=binding.sleepTimepicker.getHour();
                LoginViewModel.ViewModel.sleepMinute=binding.sleepTimepicker.getMinute();
               // Toast.makeText(getContext(),LoginViewModel.ViewModel.sleepHour,Toast.LENGTH_SHORT);
            }
        });
      //  viewModel=new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        //Log.i("name viewmodel", LoginViewModel.ViewModel.Name);
    }

}