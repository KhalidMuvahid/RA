package com.example.listviewandspinearview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.example.listviewandspinearview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_DESCRIPTION = "key_description";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSimpleListAdapter();
    }

    private void setSimpleListAdapter() {
        List<HashMap<String,String>> list = new ArrayList<>();

        for (int i=0;i<10;i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put(KEY_TITLE,"title "+i);
            map.put(KEY_DESCRIPTION,"description "+i);
            list.add(map);
        }


        /*simple adapter allow us to use checkable(Boolean) text(String) and local image(integer)
        * and one click listener for hole item view*/
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                list,
                android.R.layout.simple_list_item_2,
                new String[]{KEY_TITLE,KEY_DESCRIPTION},
                new int[] {android.R.id.text1,android.R.id.text2});
        binding.listView.setAdapter(simpleAdapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(list.get(position).get(KEY_TITLE))
                        .setMessage("you message is "+list.get(position).get(KEY_DESCRIPTION))
                        .setPositiveButton("ok", (dialog1, which) -> {})
                        .create();

                dialog.show();
            }
        });
    }
}