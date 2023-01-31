package com.example.activitynavigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;

import com.example.activitynavigation.databinding.ActivityMainBinding;
import com.example.activitynavigation.model.Option;

public class MenuActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    ActivityMainBinding binding;
    Option option;
    public static String OPTION_KEY = "option" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("______________onCreate_____________________");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState != null){
            option = savedInstanceState.getParcelable(OPTION_KEY);
        }else{
            option = new Option(3,false);
        }

        binding.openBoxButton.setOnClickListener(v->{onOpenBox();});
        binding.optionsButton.setOnClickListener(v->{onOptions();});
        binding.aboutButton.setOnClickListener(v->{onAbout();});
        binding.exitButton.setOnClickListener(v->{onExit();});
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(OPTION_KEY,option);
    }

    private void onExit() {
        finish();
    }

    private void onAbout() {
        Intent intent = new Intent(MenuActivity.this,AboutActivity.class);
        startActivity(intent);
    }

    private void onOptions() {
        Intent intent = new Intent(MenuActivity.this,OptionsActivity.class);
        intent.putExtra(OPTION_KEY,option);
        startActivityForResult(intent,MenuActivity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            Option extraOption = data.getParcelableExtra(OptionsActivity.EXTRA_OPTION);
            option = extraOption;
            System.out.println(extraOption.box+"___________________ On Activity REsult ___________________________"+option.box);

        }
    }

    private void onOpenBox() {
        Intent intent = new Intent(this,BoxActivity.class);
        intent.putExtra(OPTION_KEY,option);
        startActivity(intent);
    }
}