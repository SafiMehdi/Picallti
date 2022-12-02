package com.example.sidebar.ui.langues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sidebar.databinding.FragmentLanguesBinding;
import com.example.sidebar.databinding.FragmentLanguesBinding;

public class LanguesFragment extends Fragment {

    private FragmentLanguesBinding binding;
    private RadioGroup radioGroup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LanguesViewModel slideshowViewModel =
                new ViewModelProvider(this).get(LanguesViewModel.class);

        binding = FragmentLanguesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLangues;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

        // on below line we are initializing our variables.
        radioGroup = findViewById(R.id.languages_radio_grp);

        // on below line we are adding check change listener for our radio group.
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // on below line we are getting radio button from our group.
                RadioButton radioButton = findViewById(checkedId);

                // on below line we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Selected Radio Button is : " + radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}