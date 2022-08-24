package com.example.st.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.widget.TimePicker;

import com.example.st.R;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class LoginActivity extends AppCompatActivity {
    //LoginViewModel viewModel=new ViewModelProvider(this).get(LoginViewModel.class);
    Fragment pickSleepTimeFragment = new pickSleepTimeFragment();
    Fragment pickWakeupTime = new pickWakeTimeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TimePicker sleeppicker=findViewById(R.id.sleepTimepicker);

        AnimatedBottomBar animatedbar = findViewById(R.id.bar2);
        animatedbar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                if(tab1.getId() == R.id.sleepTime)
                    replaceFragment(pickSleepTimeFragment);
                else if (tab1.getId() == R.id.waketime){
                    replaceFragment(pickWakeupTime);
                }
            }
            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }

        });

    }
    public void replaceFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragmentContainerView2,fragment)
                .commit();

    }
}