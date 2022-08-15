package com.example.st;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class LoginActivity extends AppCompatActivity {
    Fragment pickSleepTimeFragment = new pickSleepTimeFragment();
    Fragment pickWakeupTime = new pickWakeTimeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnimatedBottomBar animatedbar = findViewById(R.id.bar2);
        animatedbar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
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