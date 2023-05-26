package com.example.harrypoterphrase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.harrypoterphrase.databinding.FragmentCounterBinding


class CounterFragment : Fragment() {


    lateinit var binding: FragmentCounterBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCounterBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.screenText.text = getString(R.string.screenText,getCounterValue())
        binding.randomText.text = getQuote()
        binding.nextBt.setOnClickListener { launchNext() }
        binding.backBt.setOnClickListener { goBack() }
    }

    private fun getQuote(): String {
        return requireArguments().getString(ARG_QUOTE)!!
    }

    private fun getCounterValue(): Int {
        return  requireArguments().getInt(ARG_COUNTER_VALUE)

    }

    private fun goBack() {
        requireActivity().onBackPressed()
    }

    private fun launchNext(){
        val screenCount = (requireActivity() as MainActivity).getScreenCount() + 1
        val quote = (requireActivity() as MainActivity).createQuote()
        val fragment = newInstance(screenCount,quote)
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container,fragment)
            .commit()
    }

    companion object{
        private val ARG_QUOTE = "argQuote"
        private val ARG_COUNTER_VALUE = "argCounter"


        fun newInstance(counterValue:Int,quote:String):CounterFragment{
            val args = Bundle().apply {
                putInt(ARG_COUNTER_VALUE,counterValue)
                putString(ARG_QUOTE,quote)
            }

            val fragment = CounterFragment().apply { arguments = args }
            fragment.arguments = args
            return fragment
        }
    }

}

