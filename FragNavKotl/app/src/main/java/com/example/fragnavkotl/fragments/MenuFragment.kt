package com.example.fragnavkotl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragnavkotl.MainActivity
import com.example.fragnavkotl.R
import com.example.fragnavkotl.databinding.FragmentMenuBinding
import com.example.fragnavkotl.fragments.contracts.navigator
import com.example.fragnavkotl.model.Option

class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding
    lateinit var option: Option

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        option = savedInstanceState?.getParcelable(KEY_OPTION ) ?: Option.DEFAULT

        binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        binding.startBt.setOnClickListener { showSelectionScreen() }
        binding.optionBt.setOnClickListener { showOption() }
        return binding.root
    }

    private fun showOption() {
        navigator().showOptionScreen(option)
    }

    private fun showSelectionScreen() {
        navigator().showBoxSelectionScreen(option)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTION,option)
    }

    companion object{
        const val KEY_OPTION = "option"
    }
}
