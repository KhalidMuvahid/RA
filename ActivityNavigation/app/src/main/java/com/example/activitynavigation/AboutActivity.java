package com.example.activitynavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.activitynavigation.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {

    ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView4.setText(this.getApplicationInfo().loadLabel(this.getPackageManager()).toString());
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(),0);
            binding.textView5.setText(info.versionName);
            binding.textView6.setText(""+info.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        binding.button.setOnClickListener(v->{
            finish();
        });
    }
}