package com.example.listviewandspinearview.baseadapter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.example.listviewandspinearview.ArrayAdapterActivity;
import com.example.listviewandspinearview.R;
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

        /*set on item click listener is not work with spinner
        *but we can use onItemSelected Listener
        *   * */
        /*
        binding.listView.setOnItemClickListener((parent, view, position, id) -> {
            showDialogInfo((Character) adapter.getItem(position));
        });
        */

        binding.listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Character character = (Character) adapter.getItem(position);
                binding.selectedItem.setText(getString(R.string.selectedItemText,character.name,character.id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                binding.selectedItem.setText("Spinner is empty");
            }
        });
    }

    private void showDialogInfo(Character info) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Information")
                .setMessage(getString(R.string.character_info,info.name,info.id))
                .setNegativeButton("ok", (dialog1, which) -> {})
                .create();

        dialog.show();
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
