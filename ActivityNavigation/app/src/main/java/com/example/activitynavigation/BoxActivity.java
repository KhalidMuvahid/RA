package com.example.activitynavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.example.activitynavigation.databinding.ActivityBoxBinding;
import com.example.activitynavigation.databinding.BoxItemsBinding;
import com.example.activitynavigation.model.Option;

import java.util.ArrayList;
import java.util.List;

import kotlin.random.Random;

public class BoxActivity extends AppCompatActivity {

    private ActivityBoxBinding binding;
    private Option option;
    private int boxIndex;
    private long timerStartTimesStamp;
    private long TIMER_DURATION = 10_000L;
    private boolean alreadyDone;
    private TimerHandler timerHandler;
    private final String KEY_START_TIMESTAMP = "stamp";
    private final String KEY_ALREADY_DONE = "KEY_ALREADY_DONE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (savedInstanceState != null) {
            option = savedInstanceState.getParcelable("options");
            boxIndex = savedInstanceState.getInt("box_index");
        } else {
            option = getIntent().getParcelableExtra(MenuActivity.OPTION_KEY);
            boxIndex = Random.Default.nextInt(option.box);
        }

        if (option.timeEnable) {
            timerHandler = new TimerHandler();
        } else {
            timerHandler = null;
        }

        if (timerHandler != null) {
            timerHandler.create(savedInstanceState);
        }

        createBox();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (timerHandler != null) {
            timerHandler.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timerHandler != null) {
            timerHandler.onStop();
        }
    }


    private void showTimerEndDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Time gone")
                .setMessage("Unfortunately you didn't manage to guess the correct box")
                .setPositiveButton("ok", (dialog, which) -> {
                })
                .create();

        alertDialog.show();
    }

    private void updateTimerUi() {
        if (getRemainingSeconds() >= 0) {
            binding.timer.setVisibility(View.VISIBLE);
            binding.text.setText(getString(R.string.time, getRemainingSeconds()));
        } else {
            binding.timer.setVisibility(View.GONE);
        }
    }

    private long getRemainingSeconds() {
        long finishAt = timerStartTimesStamp + TIMER_DURATION;
        return Long.max(0, (finishAt - System.currentTimeMillis()) / 1000);
    }

    private void createBox() {
        List<BoxItemsBinding> boxItemsBindings = new ArrayList<>();
        for (int i = 0; i < option.box; i++) {
            BoxItemsBinding boxBind = BoxItemsBinding.inflate(getLayoutInflater());
            boxBind.getRoot().setId(View.generateViewId());
            boxBind.boxTitle.setText(getString(R.string.box_title, i + 1));
            boxBind.getRoot().setOnClickListener(v -> onBoxSelected(v));
            boxBind.getRoot().setTag(i);
            binding.getRoot().addView(boxBind.getRoot());
            boxItemsBindings.add(boxBind);
        }

        int[] boxItemsBindingRef = new int[boxItemsBindings.size()];


        for (int i = 0; i < boxItemsBindings.size(); i++) {
            boxItemsBindingRef[i] = boxItemsBindings.get(i).getRoot().getId();
        }

        binding.flow.setReferencedIds(boxItemsBindingRef);

    }

    private void onBoxSelected(View view) {
        if ((int) view.getTag() == boxIndex) {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You are choose incorrect box", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("options", option);
        outState.putInt("box_index", boxIndex);
        outState.putLong(KEY_START_TIMESTAMP, timerStartTimesStamp);
        outState.putBoolean(KEY_ALREADY_DONE, alreadyDone);
    }


    class TimerHandler {

        CountDownTimer timer;

        public void create(Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                timerStartTimesStamp = savedInstanceState.getLong(KEY_START_TIMESTAMP);
                alreadyDone = savedInstanceState.getBoolean(KEY_ALREADY_DONE);
            } else {
                timerStartTimesStamp = System.currentTimeMillis();
                alreadyDone = false;
            }
        }

        void onStart() {
            if (alreadyDone) return;

            timer = new CountDownTimer(getRemainingSeconds() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimerUi();
                }

                @Override
                public void onFinish() {
                    updateTimerUi();
                    showTimerEndDialog();
                }
            };
            updateTimerUi();
            timer.start();
        }

        void onStop() {
            timer.cancel();
        }


    }
}