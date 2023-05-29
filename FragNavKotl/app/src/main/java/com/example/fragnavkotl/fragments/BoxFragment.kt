package com.example.fragnavkotl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragnavkotl.R
import com.example.fragnavkotl.fragments.contracts.HasCustomTitle
import com.example.fragnavkotl.model.Option

class BoxFragment: Fragment(),HasCustomTitle {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_box, container, false)
    }

    companion object{
        fun newInstance(option: Option):BoxFragment{
            val bundle = Bundle().apply {
                putParcelable(MenuFragment.KEY_OPTION,option)
            }

            val fragment = BoxFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getTitleRes(): Int {
        return R.string.boxTitle
    }

}