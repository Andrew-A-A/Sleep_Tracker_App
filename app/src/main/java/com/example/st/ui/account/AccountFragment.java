package com.example.st.ui.account;

import android.content.Intent;
import android.database.Cursor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.st.AppActivity;
import com.example.st.Database.SleepTrackerDatabase;
import com.example.st.R;
import com.example.st.databinding.FragmentAccountBinding;
import com.example.st.ui.login.LoginActivity;
import com.example.st.ui.login.LoginViewModel;
import com.example.st.ui.login.loginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AccountFragment extends Fragment implements AdapterView.OnItemClickListener {
    BottomNavigationView navBar;
    private FragmentAccountBinding binding;
    SleepTrackerDatabase database;
    Cursor cursor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root=binding.getRoot();
        database=new SleepTrackerDatabase(getActivity());
        navBar=root.findViewById(R.id.nav_view);
        final TextView textView = binding.textNotifications;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cursor=database.ViewCurrentUserData(LoginViewModel.ViewModel.currentEmail);
        cursor.moveToFirst();
        String [] listText={"Name: "+cursor.getString(1),"Email: "+cursor.getString(0),
                "Sleeping Time: "+String.valueOf(cursor.getInt(3))+":"+String.valueOf(cursor.getInt(4)),
                "Wakeup Time: "+String.valueOf(cursor.getInt(5))+":"+String.valueOf(cursor.getInt(6)),
                "Go to DashBoard","Log Out"};
        ListView listView=binding.accountList;
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,listText);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        cursor=database.ViewCurrentUserData(LoginViewModel.ViewModel.currentEmail);
        cursor.moveToFirst();
        if(position==0)
        {
            Toast.makeText(getContext(),"Hello Dear "+cursor.getString(1),Toast.LENGTH_SHORT).show();
        }
        if (position==2)
        {
            ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,new UpdateSleepingTimeFragment()).commit();
        }
        if(position==3)
        {
            ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,new UpdateWakeUpTimeFragment()).commit();
        }
        if (position==5)
        {
            //Intent i = new Intent(getActivity(), LoginActivity.class);
            //startActivity(i);

            //Intent intent = new Intent(getActivity(), LoginActivity.class);
            //startActivity(intent);
            //getActivity().finish();
            ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container,new loginFragment()).commit();
            navBar.setVisibility(View.GONE);

        }
    }
}