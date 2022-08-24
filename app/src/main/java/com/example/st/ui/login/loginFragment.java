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

import com.example.st.AppActivity;
import com.example.st.Database.SleepTrackerDatabase;
import com.example.st.R;
import com.example.st.databinding.FragmentLoginBinding;


public class loginFragment extends Fragment {
    FragmentLoginBinding binding;
    SleepTrackerDatabase db;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentLoginBinding.inflate(inflater,container,false);
        db=new SleepTrackerDatabase(getActivity());
        // Inflate the layout for this fragment
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
                                                              @Override
                                                              public void onClick(View view) {
                                                                  String email= binding.editTextTextPersonName.getText().toString();
                                                                  String password=binding.editTextTextPassword.getText().toString();
                                                                  LoginViewModel.ViewModel.currentEmail=email;
                                                                  if (!email.isEmpty() && !password.isEmpty())
                                                                  {
                                                                      Boolean res=db.CheckUser(email);
                                                                      if (res)
                                                                      {
                                                                          Boolean correctPassword=db.CheckUsersPassword(email,password);
                                                                          if(correctPassword)
                                                                          {
                                                                              Toast.makeText(getContext(),"Welcome Back Dear Friend <3 :)",Toast.LENGTH_SHORT).show();
                                                                              Intent i = new Intent(getActivity(), AppActivity.class);
                                                                              startActivity(i);

                                                                          }
                                                                          else
                                                                          {
                                                                              Toast.makeText(getContext(),"Incorrect Password Please Try Again :( ",Toast.LENGTH_SHORT).show();

                                                                          }
                                                                      }
                                                                      else
                                                                      {
                                                                          Toast.makeText(getContext(),"Incorrect Email Please Try Again :)",Toast.LENGTH_SHORT).show();

                                                                      }
                                                                  }
                                                                  else
                                                                  {
                                                                      Toast.makeText(getContext(),"Please Enter your Email and password !",Toast.LENGTH_SHORT).show();
                                                                  }



                                                              }
                                                          }
        );
    }
}