package com.example.st;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.st.ui.login.loginFragment;
import com.example.st.ui.login.signupFragment;

import nl.joery.animatedbottombar.AnimatedBottomBar;


public class IntroFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment loginFragment = new loginFragment();
        Fragment signupFragment = new signupFragment();
        AnimatedBottomBar animatedbar = view.findViewById(R.id.bottom_bar);
        animatedbar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                if(tab1.getId() == R.id.login)
                    replaceFragment(loginFragment);
                else if (tab1.getId() == R.id.signUp){
                    replaceFragment(signupFragment);
                }
            }
            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }

        });
    }
    public void replaceFragment(Fragment fragment){

        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().
                replace(R.id.fragmentContainerView,fragment)
                .commit();

    }
}