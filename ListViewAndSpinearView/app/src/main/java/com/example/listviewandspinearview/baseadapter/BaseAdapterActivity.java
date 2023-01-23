package com.example.listviewandspinearview.baseadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

import com.example.listviewandspinearview.ArrayAdapterActivity;
import com.example.listviewandspinearview.databinding.ActivityBaseAdapterBinding;
import com.example.listviewandspinearview.databinding.AddDilogBinding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

import kotlin.random.Random;


public class BaseAdapterActivity extends AppCompatActivity implements OnDeleteItemListener {

    private ActivityBaseAdapterBinding binding;
    private ArrayList<Character> data;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseAdapterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        data = new ArrayList();
        data.add(new Character(Random.Default.nextInt(100), "Sabziro",false));
        data.add(new Character(Random.Default.nextInt(100),"Josh",false));
        data.add(new Character(Random.Default.nextInt(100),"Bob",false));
        data.add(new Character(Random.Default.nextInt(100),"Milly",false));
        adapter = new CharacterAdapter(data,this);
        binding.listView.setAdapter(adapter);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    @Override
    public void delete(Character character) {
        data.remove(character);
        adapter.notifyDataSetChanged();
    }

    private void addItem() {

        AddDilogBinding binding = AddDilogBinding.inflate(getLayoutInflater());

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Create Character")
                .setView(binding.getRoot())
                .setPositiveButton("add", (dialog1, which) -> {
                    String name = binding.addEditText.getText().toString();
                    if (!name.isEmpty()){
                        createCharacter(name);
                    }
                })
                .create();

        dialog.show();
    }

    private void createCharacter(String name) {
        Character character = new Character(Random.Default.nextInt(100),name,true);
        data.add(character);
        adapter.notifyDataSetChanged();

    }


}

interface OnDeleteItemListener{
    void delete(Character character);
}
