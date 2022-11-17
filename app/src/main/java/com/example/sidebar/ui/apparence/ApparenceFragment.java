package com.example.sidebar.ui.apparence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sidebar.databinding.FragmentApparenceBinding;

public class ApparenceFragment extends Fragment {

    private FragmentApparenceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ApparenceViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ApparenceViewModel.class);

        binding = FragmentApparenceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textApparence;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}