package com.example.activitystates;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.activitystates.databinding.ActivitySimpleState4Binding;

import java.io.Serializable;

import kotlin.random.Random;


public class SimpleState4Activity extends AppCompatActivity {

    private ActivitySimpleState4Binding binding;

    private SimpleState4ActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleState4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(SimpleState4ActivityViewModel.class);



        binding.incrementButton.setOnClickListener(v -> viewModel.incrementing());

        binding.switchVisibilityButton.setOnClickListener(v -> viewModel.switchVisibility());

        binding.changeColorButton.setOnClickListener(v -> viewModel.changeColor());

        if (viewModel.get().getValue() == null){
            viewModel.initState(new Statee(View.VISIBLE,0,Color.GREEN));
        }

        viewModel.get().observe(this, new Observer<Statee>() {
            @Override
            public void onChanged(Statee statee) {
                updateUI(statee);
            }
        });
    }

    private void updateUI(Statee statee) {
        binding.textView.setText(String.valueOf(statee.count));
        binding.textView.setTextColor(statee.color);
        binding.textView.setVisibility(statee.visibility);
    }
}