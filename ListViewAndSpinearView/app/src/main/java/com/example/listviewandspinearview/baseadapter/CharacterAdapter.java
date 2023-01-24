package com.example.listviewandspinearview.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.listviewandspinearview.databinding.BaseAdapterItemBinding;

import java.util.List;

public class CharacterAdapter extends BaseAdapter implements View.OnClickListener {
    List<Character> characters;
    OnDeleteItemListener listener;

    CharacterAdapter(List<Character> data, OnDeleteItemListener listener){
        characters = data;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return characters.size();
    }

    @Override
    public Character getItem(int position) {
        return characters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return characters.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return defaultView(position,convertView,parent,true);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return defaultView(position,convertView,parent,false);
    }

    public View defaultView(int position, View convertView, ViewGroup parent,boolean isDropped) {
        BaseAdapterItemBinding binding;
        if (convertView == null){
            binding = createBinding(parent.getContext());
        }else{
            binding = (BaseAdapterItemBinding) convertView.getTag();
        }

        Character character = getItem(position);

        binding.textView.setText(character.name);
        binding.deleteButton.setTag(character);
        if (isDropped){
            binding.deleteButton.setVisibility(View.VISIBLE);
        }else {
            binding.deleteButton.setVisibility(View.GONE);
        }
        return binding.getRoot();
    }


    private BaseAdapterItemBinding createBinding(Context context) {
        BaseAdapterItemBinding binding  = BaseAdapterItemBinding.inflate(LayoutInflater.from(context));
        binding.deleteButton.setOnClickListener(this);
        binding.getRoot().setTag(binding);
        return binding;
    }

    @Override
    public void onClick(View v) {
        Character character =(Character) v.getTag();
        listener.delete(character);
    }
}
