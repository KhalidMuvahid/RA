package com.example.activitystates;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activitystates.databinding.ActivitySimpleState3Binding;

import java.io.Serializable;

import kotlin.random.Random;


public class SimpleState3Activity extends AppCompatActivity {

    private static final String KEY_STATE = "key";
    private ActivitySimpleState3Binding binding;
    private State state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleState3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.incrementButton.setOnClickListener(v -> incrementing());

        binding.switchVisibilityButton.setOnClickListener(v -> switchVisibility());

        binding.changeColorButton.setOnClickListener(v -> changeColor());

        if (savedInstanceState ==null){
            state = new State(View.VISIBLE,0,Color.GREEN);
        }else {
            state = (State) savedInstanceState.getSerializable(KEY_STATE);
        }
        updateUI();

        binding.nextButton.setOnClickListener(v -> startActivity(new Intent(SimpleState3Activity.this,SimpleState4Activity.class)));
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_STATE,state);
    }

    private void changeColor() {
        state.color = Color.rgb(
                Random.Default.nextInt(256),
                Random.Default.nextInt(256),
                Random.Default.nextInt(256)
        );
        binding.textView.setTextColor(state.color);
    }

    private void switchVisibility() {
        if (state.visibility == View.VISIBLE){
            state.visibility = View.GONE;
            binding.textView.setVisibility(state.visibility);
        }else{
            state.visibility = View.VISIBLE;
            binding.textView.setVisibility(state.visibility);
        }
    }

    private void incrementing() {
        state.count++;
        binding.textView.setText(""+state.count);
    }



    private void updateUI() {
        binding.textView.setText(String.valueOf(state.count));
        binding.textView.setTextColor(state.color);
        binding.textView.setVisibility(state.visibility);
    }
}

class State implements Serializable {
    int visibility;
    int count;
    int color;

    public State(int visibility,int count,int color){
        this.visibility = visibility;
        this.color = color;
        this.count = count;
    }
}


/*Parcelable 1
class StateP1 implements Parcelable {
    int visibility;
    int count;
    int color;

    public StateP1(int visibility, int count, int color){
        this.visibility = visibility;
        this.color = color;
        this.count = count;
    }

    protected StateP1(Parcel in) {
        visibility = in.readInt();
        count = in.readInt();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(visibility);
        dest.writeInt(count);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StateP1> CREATOR = new Creator<StateP1>() {
        @Override
        public StateP1 createFromParcel(Parcel in) {
            return new StateP1(in);
        }

        @Override
        public StateP1[] newArray(int size) {
            return new StateP1[size];
        }
    };
}*/

/*@Parsalize
class StateP2 implements Parcelable {
    int visibility;
    int count;
    int color;

    public State(int visibility,int count,int color){
        this.visibility = visibility;
        this.color = color;
        this.count = count;
    }
}*/

