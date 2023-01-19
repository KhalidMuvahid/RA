package com.example.baseuicomponents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.baseuicomponents.databinding.ActivityMainViewBindingBinding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import kotlin.random.Random;

public class MainActivityViewBinding extends AppCompatActivity {

    private ActivityMainViewBindingBinding binding;

    private boolean searching = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainViewBindingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*first component*/
        binding.textView.setTextColor(Color.GREEN);

        /*second component*/
        binding.imageView.setBackgroundResource(R.drawable.background);
        binding.imageView.setImageResource(R.drawable.ic_launcher_foreground);

        binding.imageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.image_width);
        binding.imageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.image_height);

        binding.imageView.requestLayout();
        Glide.with(this)
                .load("https://source.unsplash.com/random/800x400")
                .into(binding.imageView);


        /*third ui comp that i learned*/
        binding.button.setOnClickListener(v->{
            getRandomImage();});

        binding.button.setOnLongClickListener(v->{return showToastWithRandomNumber();});


        /*fourth ui comp that i learned*/
        binding.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    return getRandomImage();
                }
                return false;
            }
        });

        binding.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searching = binding.checkbox.isChecked();
                updateUI();
            }
        });

        updateUI();


    }

    private void updateUI() {
        if (searching){
            binding.editText.setVisibility(View.VISIBLE);
        }else{
            binding.editText.setVisibility(View.GONE);
        }
    }

    private boolean getRandomImage(){
        String encoderKeyWord = null;

        int checkedId = binding.radioGroup.getCheckedRadioButtonId();
        String keyWorld = binding.editText.getText().toString();
        if(searching && keyWorld.isEmpty()){
            binding.editText.setError("Key word is empty");
            return true;
        }else{
            RadioButton radioButton = binding.radioGroup.findViewById(checkedId);
            encoderKeyWord = radioButton.getText().toString();
        }

        if (encoderKeyWord == null){
            if (searching){
                try {
                    encoderKeyWord = URLEncoder.encode(keyWorld, StandardCharsets.UTF_8.name());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                encoderKeyWord="";
            }
        }


        binding.progressCircular.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load("https://source.unsplash.com/random/800x400?"+encoderKeyWord)
                .skipMemoryCache(true)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        binding.progressCircular.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        binding.progressCircular.setVisibility(View.GONE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.imageView);
        return false;
    }

    private boolean showToastWithRandomNumber() {
        int number = Random.Default.nextInt(100);
        String message = getString(R.string.random_number,number);
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        return true;
    }
}