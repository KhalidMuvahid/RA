package com.example.fragnavkotl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.SimpleAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import com.example.fragnavkotl.R
import com.example.fragnavkotl.databinding.FragmentOptionsBinding
import com.example.fragnavkotl.fragments.contracts.CustomAction
import com.example.fragnavkotl.fragments.contracts.HasCustomAction
import com.example.fragnavkotl.fragments.contracts.HasCustomTitle
import com.example.fragnavkotl.fragments.contracts.navigator
import com.example.fragnavkotl.model.Option


class OptionFragment : Fragment(), HasCustomTitle, HasCustomAction {

    private lateinit var binding: FragmentOptionsBinding
    lateinit var option: Option

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        option =
            savedInstanceState?.getParcelable(MenuFragment.KEY_OPTION) ?:
            arguments?.getParcelable(MenuFragment.KEY_OPTION) ?:
            throw IllegalArgumentException("You need to specify option to lunch this fragment")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)


        binding.cancelButton.setOnClickListener { onClickCancel() }
        binding.confirmButton.setOnClickListener { onConfirmPressed() }
        binding.enableTime.setOnCheckedChangeListener { _, isChecked -> option.timeEnable = isChecked }

        val boxCountItem = (1..6).map { BoxCountItem(it,resources.getQuantityString(R.plurals.boxes,it,it)) }
        val spinAdapter = ArrayAdapter(requireActivity(),R.layout.item_spinner,boxCountItem)

        binding.spinner.adapter = spinAdapter

        return binding.root
    }

    private fun onClickCancel() {
        navigator().goBack()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(MenuFragment.KEY_OPTION, option)
    }

    override fun getCustomAction(): CustomAction {
        return CustomAction(
            R.drawable.ic_baseline_check_24,
            R.string.menu_text,
            Runnable {
                onConfirmPressed()
            }
        )
    }

    override fun getTitleRes(): Int {
        return R.string.optionTex
    }

    private fun onConfirmPressed() {

    }

    companion object {
        fun newInstance(option: Option): OptionFragment {
            val args = Bundle().apply { putParcelable(MenuFragment.KEY_OPTION, option) }
            val fragment = OptionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    class BoxCountItem(
        val count: Int,
        private val optionTitle: String
    ) {
        override fun toString(): String {
            return optionTitle
        }
    }

}