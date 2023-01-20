package com.example.activitystates;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.activitystates.databinding.ActivitySimpleState4Binding;

import java.io.Serializable;

import kotlin.random.Random;


public class SimpleState4ActivityViewModel extends ViewModel {

    LiveData<Statee> get(){
        return stateLiveDate;
    }
    private MutableLiveData<Statee> stateLiveDate= new MutableLiveData<Statee>();


    public void initState(Statee state){
        stateLiveDate.setValue(state);
    }

    public void incrementing() {
        Statee oldState = stateLiveDate.getValue();
        stateLiveDate.setValue(new Statee(oldState.visibility, oldState.count+=1, oldState.color));
    }

    public void changeColor() {
        Statee oldState = stateLiveDate.getValue();
        int color = Color.rgb(
                Random.Default.nextInt(256),
                Random.Default.nextInt(256),
                Random.Default.nextInt(256)
        );
        stateLiveDate.setValue(new Statee(oldState.visibility, oldState.count, color));



    }

    public void switchVisibility() {
        Statee oldState = stateLiveDate.getValue();
        if (stateLiveDate.getValue().visibility == View.VISIBLE){
            stateLiveDate.setValue(new Statee(View.GONE, oldState.count, oldState.color));
        }else{
            stateLiveDate.setValue(new Statee(View.VISIBLE, oldState.count, oldState.color));

        }
    }

}

class Statee implements Serializable {
    int visibility;
    int count;
    int color;

    public Statee(int visibility,int count,int color){
        this.visibility = visibility;
        this.color = color;
        this.count = count;
    }
}