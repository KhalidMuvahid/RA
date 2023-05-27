package com.example.fragmentnavigationkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragnavkotl.databinding.FragmentAboutBinding

class AboutFragment : Fragment(){
    lateinit var binding: FragmentAboutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAboutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.applicationName.text = requireActivity().applicationInfo.loadLabel(requireActivity().packageManager)
        binding.versionName.text = requireActivity().packageManager.getPackageInfo(requireActivity().packageName,0).versionName
        binding.versionCode.text = requireActivity().packageManager.getPackageInfo(requireActivity().packageName,0).versionCode.toString()

    }



}