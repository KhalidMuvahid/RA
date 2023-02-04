package com.example.fragmentnavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.fragmentnavigation.databinding.FragmentOptionBinding;
import com.example.fragmentnavigation.model.Option;

import java.util.ArrayList;

public class OptionFragment extends Fragment {

    private FragmentOptionBinding binding;
    private static final String KEY_TITLE = "key";
    private Option option;
    private ArrayList<Integer> data;
    public static final String EXTRA_OPTION = "box_count";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding =  FragmentOptionBinding.inflate(inflater,container,false);

        if (getArguments() != null){
            option = getArguments().getParcelable(MenuFragment.OPTION_KEY);
        }else {
        }

        setUpSpinner();
        updateUI();


        binding.confirmButton.setOnClickListener(v -> {
            option.timeEnable = binding.enableTime.isChecked();
            Bundle extra = new Bundle();
            extra.putParcelable(EXTRA_OPTION,option);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,MenuFragment.getInstance(extra)).commit();
        });

        binding.cancelButton.setOnClickListener(v->{
//           getActivity().getSupportFragmentManager().beginTransaction().addToBackStack();
        });

        return binding.getRoot();

    }

    private void setUpSpinner(){
        data = new ArrayList<>();

        for (int i=1;i<=6;i++){
            data.add(i);
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1,data);
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


    static OptionFragment getInstance(Bundle bundle){
        OptionFragment fragment = new OptionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}