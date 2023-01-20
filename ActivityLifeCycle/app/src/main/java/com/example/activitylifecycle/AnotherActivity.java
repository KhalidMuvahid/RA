package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Log.d(MainActivity.TAG,this.getClass().getSimpleName()+" onCreate");

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