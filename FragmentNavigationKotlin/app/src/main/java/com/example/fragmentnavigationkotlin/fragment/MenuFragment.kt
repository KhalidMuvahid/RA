package com.example.fragmentnavigationkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentnavigationkotlin.R
import com.example.fragmentnavigationkotlin.contract.ResultListener
import com.example.fragmentnavigationkotlin.contract.navigator
import com.example.fragmentnavigationkotlin.databinding.FragmentMenuBinding
import com.example.fragmentnavigationkotlin.model.Option

class MenuFragment : Fragment() {
    private lateinit var binding:FragmentMenuBinding
    private lateinit var option:Option
    private val OPTIONS= "Option"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuBinding.inflate(inflater,container,false)
        navigator().listenerResult(Option::class.java,viewLifecycleOwner){
            this.option = it
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        option = if (savedInstanceState == null){
            Option.DEFAULT
        }else{
            savedInstanceState.getParcelable(OPTIONS)!!
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openBoxButton.setOnClickListener { onOpenBox() }
        binding.optionsButton.setOnClickListener { onOptions() }
        binding.aboutButton.setOnClickListener { onAbout() }
        binding.exitButton.setOnClickListener { onExit() }
    }

    private fun onAbout() {
        navigator().showAboutScreen()
    }

    private fun onOptions() {
        navigator().showOptionScreen(option)
    }

    private fun onOpenBox() {
        navigator().showBoxSelectScreen(option)
    }

    private fun onExit() {
        navigator().goBack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(OPTIONS,option)
    }
}