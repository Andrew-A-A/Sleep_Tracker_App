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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.st.Database.SleepTrackerDatabase;
import com.example.st.R;
import com.example.st.databinding.FragmentAccountBinding;

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

        if(position==0)
        {
            cursor.moveToFirst();
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
        if(position==4)
        {
            Cursor cursor=database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
            if(cursor.getCount()!=0) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("Cycles:" + "\n");
                buffer.append("Average Number of Sleeping Cycles:" + getTotalCycles() / GetTotal() + "\n");
                buffer.append("Total Number of Sleeping Cycles:" + getTotalCycles() + "\n");
                buffer.append("Rating:" + "\n");
                buffer.append("Average Rating: " + AvgRating() + "\n");
                buffer.append("Total Hours of Sleep: " + (GetTotalSleepingTime() / 60) + "\n");
                buffer.append("Sleeping Time:" + "\n");
                buffer.append("Average Sleeping Time: " + GetAvgSleepingTime() + "\n");
                buffer.append("Waking Up Time:" + "\n");
                buffer.append("Average Waking Up Time: " + GetAvgWakingUpTime() + "\n");
                buffer.append("_________________________");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("User Entry Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }
            else{
                Toast.makeText(getContext(),"No Data",Toast.LENGTH_SHORT).show();
            }
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
    public int GetTotal()
    {
        Cursor cursor=database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
        return cursor.getCount();
    }
    public float getTotalCycles()
    {
        Cursor cursor = database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
        float cycles=0;
        while (cursor.moveToNext()) {
            cycles+=cursor.getFloat(6);
        }
        return cycles;
    }
    public String AvgRating()
    {
        Cursor cursor=database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
        int excellent=0;
        int good=0;
        int bad=0;
        while (cursor.moveToNext())
        {
            if(cursor.getString(7)=="Excellent")
            {
                excellent++;
            }
            else if(cursor.getString(7)=="Good")
            {
                good++;
            }
            else
            {
                bad++;
            }
        }
        int x=Math.max(excellent,good);
        int y=Math.max(excellent,bad);
        int z=Math.max(good,bad);

        if (excellent==good)
        {
            return "Excellent";
        }
        else if(excellent==bad)
        {
            return "Good";
        }
        else if(good==bad)
        {
            return "Good";
        }
        else
        {
            if(x==excellent && y==excellent)
            {
                return "Excellent";
            }
            else if(x==good && z==good)
            {
                return "Good";
            }
            else
            {
                return"Bad";
            }
        }
    }
    public int GetTotalSleepingTime()
    {
        Cursor cursor=database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
        int total =0;
        while (cursor.moveToNext())
        {
            total+=cursor.getInt(5);
        }
        return total;
    }
    public String GetAvgSleepingTime()
    {
        Cursor cursor=database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
        int totalHours=0,totalMin=0;
        while (cursor.moveToNext())
        {
            totalHours+=cursor.getInt(1);
            totalMin+=cursor.getInt(2);
        }
        float avghours=((totalHours*60+totalMin)/cursor.getCount())/60;
        float avgmin=(avghours*60)%60;

        String message=String.valueOf((int)avghours)+":"+String.valueOf((int) avgmin);
        return message;

    }
    public String GetAvgWakingUpTime()
    {
        Cursor cursor=database.ViewPersonalData(LoginViewModel.ViewModel.currentEmail);
        int totalHours=0,totalMin=0;
        while (cursor.moveToNext())
        {
            totalHours+=cursor.getInt(3);
            totalMin+=cursor.getInt(4);
        }
        float avghours=((totalHours*60+totalMin)/cursor.getCount())/60;
        float avgmin=(avghours*60)%60;

        String message=String.valueOf((int)avghours)+":"+String.valueOf((int) avgmin);
        return message;

    }
}
