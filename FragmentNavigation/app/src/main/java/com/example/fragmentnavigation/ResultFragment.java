package com.example.fragmentnavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentnavigation.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {

    FragmentResultBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater,container,false);
        binding.button2.setOnClickListener(v -> onToMainMenuActivity());
        return binding.getRoot();
    }

    private void onToMainMenuActivity() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new MenuFragment()).commit();
    }



}