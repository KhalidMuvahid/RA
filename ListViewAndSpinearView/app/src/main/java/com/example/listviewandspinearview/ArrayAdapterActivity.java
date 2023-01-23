package com.example.listviewandspinearview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.listviewandspinearview.databinding.ActivityArrayAdapterBinding;
import com.example.listviewandspinearview.databinding.AddDilogBinding;

import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class ArrayAdapterActivity extends AppCompatActivity {
    
    private ActivityArrayAdapterBinding binding;
    private ArrayAdapter<Character> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArrayAdapterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setArrayAdapter();

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.getItem(position) != null){
                    deleteItem((Character) adapter.getItem(position));
                }
            }
        });
        
    }

    private void deleteItem(Character item) {

       DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
           @Override
           public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE){
                    adapter.remove(item);
                }
           }
       };

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure that want to delete that element")
                .setPositiveButton("yes",listener)
                .setNegativeButton("no",listener)
                .create();

        dialog.show();
    }

    private void setArrayAdapter() {

        ArrayList<Character> data = new ArrayList();

        data.add(new Character(UUID.randomUUID().toString(),"Sabziro"));
        data.add(new Character(UUID.randomUUID().toString(),"Josh"));
        data.add(new Character(UUID.randomUUID().toString(),"Bob"));
        data.add(new Character(UUID.randomUUID().toString(),"Milly"));

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                data);

        binding.listView.setAdapter(adapter);

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
        Character character = new Character(UUID.randomUUID().toString(),name);
        adapter.add(character);

    }

    class Character{
        String id;
        String name;
        Character(String id,String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

