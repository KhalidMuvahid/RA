package com.example.fragmentnavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fragmentnavigation.databinding.BoxItemsBinding;
import com.example.fragmentnavigation.databinding.FragmentBoxBinding;
import com.example.fragmentnavigation.model.Option;

import java.util.ArrayList;
import java.util.List;

import kotlin.random.Random;

public class BoxFragment extends Fragment {

    private FragmentBoxBinding binding;
    private Option option;
    private int boxIndex;
    private long timerStartTimesStamp;
    private long TIMER_DURATION = 10_000L;
    private boolean alreadyDone;
    private TimerHandler timerHandler;
    private final String KEY_START_TIMESTAMP = "stamp";
    private final String KEY_ALREADY_DONE = "KEY_ALREADY_DONE";

    public static BoxFragment getInstance(Bundle bundle) {
        BoxFragment fragment = new BoxFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoxBinding.inflate(inflater,container,false);
        option = getArguments().getParcelable(MenuFragment.OPTION_KEY);
        boxIndex= Random.Default.nextInt(option.box);
        if (option.timeEnable) {
            timerHandler = new TimerHandler();
        } else {
            timerHandler = null;
        }

        if (timerHandler != null) {
            timerHandler.create(savedInstanceState);
        }
        createBox();
        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (timerHandler != null) {
            timerHandler.onStart();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (timerHandler != null) {
            timerHandler.onStop();
        }
    }


    private void showTimerEndDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
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
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new ResultFragment()).addToBackStack("").commit();
        } else {
            Toast.makeText(getActivity(), "You are choose incorrect box", Toast.LENGTH_SHORT).show();
        }
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




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityBoxBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        if (savedInstanceState != null) {
//            option = savedInstanceState.getParcelable("options");
//            boxIndex = savedInstanceState.getInt("box_index");
//        } else {
//            option = getIntent().getParcelableExtra(MenuActivity.OPTION_KEY);
//            boxIndex = Random.Default.nextInt(option.box);
//        }
//

//
//        createBox();
//    }







