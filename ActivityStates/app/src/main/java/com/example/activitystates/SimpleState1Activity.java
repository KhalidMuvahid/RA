package com.example.activitystates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.activitystates.databinding.ActivitySimpleState1Binding;

import kotlin.random.Random;


public class SimpleState1Activity extends AppCompatActivity {

    private ActivitySimpleState1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleState1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementing();
            }
        });

        binding.switchVisibilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchVisibility();
            }
        });

        binding.changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor();
            }
        });

        binding.nextButton.setOnClickListener(v -> startActivity(new Intent(SimpleState1Activity.this,SimpleState2Activity.class)));
    }

    private void changeColor() {
        int color = Color.rgb(
                Random.Default.nextInt(256),
                Random.Default.nextInt(256),
                Random.Default.nextInt(256)
        );
        binding.textView.setTextColor(color);
    }

    private void switchVisibility() {
        int visibility = binding.textView.getVisibility();
        if (visibility == View.VISIBLE){
            binding.textView.setVisibility(View.GONE);
        }else{
            binding.textView.setVisibility(View.VISIBLE);
        }
    }

    private void incrementing() {
        int count =Integer.parseInt(binding.textView.getText().toString());
        count++;
        binding.textView.setText(""+count);
    }
}