package com.example.st.ui.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.st.databinding.FragmentTipsBinding;

public class TipsFragment extends Fragment {

    private FragmentTipsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TipsViewModel tipsViewModel =
                new ViewModelProvider(this).get(TipsViewModel.class);

        binding = FragmentTipsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}