package com.example.fragmentnavigationkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragnavkotl.databinding.FragmentOptionsBinding


class OptionFragment : Fragment(){

    private lateinit var binding: FragmentOptionsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOptionsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




}