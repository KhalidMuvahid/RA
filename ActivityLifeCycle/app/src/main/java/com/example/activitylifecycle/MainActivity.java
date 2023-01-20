package com.example.activitylifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.activitylifecycle.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MainActivity.TAG,this.getClass().getSimpleName()+" onCreate");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.anotherButton.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,AnotherActivity.class));
        });

        binding.transparentButton.setOnClickListener(v->{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        });

        binding.minimizeTheAppButton.setOnClickListener(v->{
            moveTaskToBack(false);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MainActivity.TAG,this.getClass().getSimpleName()+" onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.TAG,this.getClass().getSimpleName()+" onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.TAG,this.getClass().getSimpleName()+" onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MainActivity.TAG,this.getClass().getSimpleName()+" onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.TAG,this.getClass().getSimpleName()+" onStop");
    }

    @Override
    protected void onDestroy() {
        Log.d(MainActivity.TAG,this.getClass().getSimpleName()+" onDestroy");
        super.onDestroy();
    }
}