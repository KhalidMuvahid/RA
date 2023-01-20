package com.example.activitystates;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activitystates.databinding.ActivitySimpleState2Binding;

import kotlin.random.Random;


public class SimpleState2Activity extends AppCompatActivity {

    private static final String INC_TEXT = "inc_text";
    private static final String COLOR_TEXT = "text_color";
    private static final String VIS_TEXT = "text_visibility";

    private ActivitySimpleState2Binding binding;
    private int count;
    private int color;
    private int visibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleState2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null ){
            count = 0;
            color = Color.RED;
            visibility = View.VISIBLE;
        } else{
            count = savedInstanceState.getInt(INC_TEXT);
            visibility = savedInstanceState.getInt(VIS_TEXT);
            color =savedInstanceState.getInt(COLOR_TEXT);
        }
        updateUI();

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

        binding.nextButton.setOnClickListener(v -> startActivity(new Intent(SimpleState2Activity.this,SimpleState3Activity.class)));
    }

    private void updateUI() {
        binding.textView.setText(String.valueOf(count));
        binding.textView.setTextColor(color);
        binding.textView.setVisibility(visibility);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(INC_TEXT,count);
        outState.putInt(COLOR_TEXT,color);
        outState.putInt(VIS_TEXT,visibility);

    }

    private void changeColor() {
        color = Color.rgb(
                Random.Default.nextInt(256),
                Random.Default.nextInt(256),
                Random.Default.nextInt(256)
        );
        binding.textView.setTextColor(color);
    }

    private void switchVisibility() {
        if (visibility == View.VISIBLE){
            visibility = View.GONE;
            binding.textView.setVisibility(View.GONE);
        }else{
            visibility = View.VISIBLE;
            binding.textView.setVisibility(View.VISIBLE);
        }
    }

    private void incrementing() {
        count++;
        binding.textView.setText(""+count);
    }
}