package com.example.sidebar.ui.apropos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sidebar.databinding.FragmentAproposBinding;

public class AproposFragment extends Fragment {

    private FragmentAproposBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AproposViewModel homeViewModel =
                new ViewModelProvider(this).get(AproposViewModel.class);

        binding = FragmentAproposBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textApropos;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}