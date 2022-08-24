package com.example.st.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.st.R;
import com.example.st.databinding.FragmentSignupBinding;


public class signupFragment extends Fragment {
    FragmentSignupBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentSignupBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.nameEditText.getText().toString();
                //Toast.makeText(view.getContext(), binding.nameEditText.getText().toString(),Toast.LENGTH_LONG).show();
                String confirmPassword=binding.confirmPasswordEditText.getText().toString();
                String password=binding.passwordEditText.getText().toString();
                String email=binding.emailEditText.getText().toString();
                LoginViewModel.ViewModel.Password=password;
                LoginViewModel.ViewModel.Name=name;
                LoginViewModel.ViewModel.currentEmail=email;
                Log.i("name viewmodel", LoginViewModel.ViewModel.Name);Log.i("name viewmodel", LoginViewModel.ViewModel.Name);
                LoginViewModel.ViewModel.Email=email;

                if (!password.isEmpty() && !email.isEmpty() && !name.isEmpty()) {
                    if (password.equals(confirmPassword)) {
                        Intent i = new Intent(getContext(), LoginActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getContext(), "Rewrite password !", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getContext(), "Please don't leave any empty field", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}