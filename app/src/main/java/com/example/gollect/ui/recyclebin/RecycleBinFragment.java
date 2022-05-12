package com.example.gollect.ui.recyclebin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gollect.databinding.FragmentProfileBinding;
import com.example.gollect.databinding.FragmentRecyclebinBinding;

public class RecycleBinFragment extends Fragment {

    private FragmentRecyclebinBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecycleBinViewModel recycleBinViewModel =
                new ViewModelProvider(this).get(RecycleBinViewModel.class);

        binding = FragmentRecyclebinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRecycleBin;
        recycleBinViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}