package com.example.fragmentnavigation;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentnavigation.databinding.FragmentMenuBinding;
import com.example.fragmentnavigation.model.Option;

public class MenuFragment extends Fragment {

    FragmentMenuBinding binding;
    public static final int REQUEST_CODE = 1;
    Option option;
    public static String OPTION_KEY = "option" ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater,container,false);
        binding.openBoxButton.setOnClickListener(v->{onOpenBox();});
        binding.optionsButton.setOnClickListener(v->{onOptions();});
        binding.aboutButton.setOnClickListener(v->{onAbout();});
        binding.exitButton.setOnClickListener(v->{onExit();});
        if (getArguments() != null){
            option = getArguments().getParcelable(OptionFragment.EXTRA_OPTION);
        }else{
            option = new Option(3,false);
        }
        return binding.getRoot();
    }

    public static MenuFragment getInstance(Bundle bundle){
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(bundle);
        return fragment;
    }



    private void onExit() {
        getActivity().finish();
    }

    private void onAbout() {
        replace(new AboutFragment());
    }

    private void onOptions() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(OPTION_KEY,option);
       replace(OptionFragment.getInstance(bundle));
    }



    private void onOpenBox() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(OPTION_KEY,option);
        replace(BoxFragment.getInstance(bundle));

    }

    private void replace(Fragment fragment){
        getParentFragmentManager().beginTransaction().replace(R.id.frag_container,fragment).commit();
    }
}
