package com.example.fragmentnavigationkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentnavigationkotlin.R
import com.example.fragmentnavigationkotlin.contract.CustomAction
import com.example.fragmentnavigationkotlin.contract.HasCustomAction
import com.example.fragmentnavigationkotlin.contract.HasCustomTitle
import com.example.fragmentnavigationkotlin.contract.navigator
import com.example.fragmentnavigationkotlin.databinding.FragmentOptionsBinding
import com.example.fragmentnavigationkotlin.model.Option

class OptionFragment : Fragment(),HasCustomTitle , HasCustomAction {

    private lateinit var binding: FragmentOptionsBinding

    private lateinit var option:Option


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOptionsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        option = savedInstanceState?.getParcelable(OPTION_ARG) ?:
        arguments?.getParcelable(OPTION_ARG) ?:
        throw java.lang.IllegalArgumentException("You need specify option to  launch this fragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmButton.setOnClickListener {
            onConfirmPressed()
        }
        binding.cancelButton.setOnClickListener {
            onCancelPressed()
        }
    }

    private fun onCancelPressed() {
        navigator().goBack()
    }

    override fun getTitle(): Int = R.string.option


    override fun getCustomAction(): CustomAction {
        return CustomAction(R.drawable.ic_baseline_check_24,
            R.string.done,
            Runnable {
                onConfirmPressed()
            })
    }

    private fun onConfirmPressed() {
        navigator().publishResult(option)
        navigator().goBack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(OPTION_ARG,option)
    }

    companion object{
        private val OPTION_ARG = "OptionArg"

        fun newInstance(option:Option): Fragment{
            val args = Bundle()

            val fragment = OptionFragment()
            args.putParcelable(OPTION_ARG,option)
            fragment.arguments = args
            return fragment
        }
    }



}