package com.example.activitynavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.activitynavigation.databinding.ActivityOptionsBinding;
import com.example.activitynavigation.model.Option;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity {

    private static final String KEY_TITLE = "key";
    private ActivityOptionsBinding binding;
    private Option option;
    private ArrayList<Integer> data;
    public static final String EXTRA_OPTION = "box_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        option=getIntent().getParcelableExtra(MenuActivity.OPTION_KEY);

        setUpSpinner();
        updateUI();


        binding.confirmButton.setOnClickListener(v -> {
            option.timeEnable = binding.enableTime.isChecked();
            Intent intent = new Intent();
            intent.putExtra(EXTRA_OPTION,option);
            setResult(RESULT_OK,intent);
            finish();
        });

        binding.cancelButton.setOnClickListener(v -> {finish();});
    }

    private void setUpSpinner(){
         data = new ArrayList<>();

        for (int i=1;i<=6;i++){
            data.add(i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,android.R.id.text1,data);
        binding.spinner.setAdapter(adapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int newBoxCount = data.get(position).intValue();
                option.box = newBoxCount;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateUI(){
        binding.enableTime.setChecked(option.timeEnable);

        int currentValue = data.indexOf(option.box);
        binding.spinner.setSelection(currentValue);
    }
}