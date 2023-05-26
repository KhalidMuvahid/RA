package com.example.fragmentnavigationkotlin

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.fragmentnavigationkotlin.contract.Navigator
import com.example.fragmentnavigationkotlin.contract.ResultListener
import com.example.fragmentnavigationkotlin.databinding.ActivityMainBinding
import com.example.fragmentnavigationkotlin.fragment.AboutFragment
import com.example.fragmentnavigationkotlin.fragment.BoxFragment
import com.example.fragmentnavigationkotlin.fragment.MenuFragment
import com.example.fragmentnavigationkotlin.fragment.OptionFragment
import com.example.fragmentnavigationkotlin.model.Option

class MainActivity: AppCompatActivity(), Navigator{

    lateinit var binding:ActivityMainBinding

    private val KEY_RESULT = "KEY_RESULT"



    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks(){
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.fragment_container,MenuFragment()).commit()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener,false)

    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showBoxSelectScreen(option: Option) {
        launchFragment(BoxFragment())
    }

    override fun showOptionScreen(option: Option) {
        launchFragment(OptionFragment.newInstance(option))
    }

    override fun showAboutScreen() {
        launchFragment(AboutFragment())
    }


    override fun goBack() {
        onBackPressed()
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun <T : Parcelable> publishResult(result: T) {
        supportFragmentManager.setFragmentResult(result.javaClass.name, bundleOf(KEY_RESULT to result))
    }

    override fun <T : Parcelable> listenerResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
       supportFragmentManager.setFragmentResultListener(clazz.name,owner, FragmentResultListener{key,bundle->
           listener.invoke(bundle.getParcelable(KEY_RESULT)!!)
       })
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment).commit()
    }


    private fun updateUi() {
        TODO("Not yet implemented")
    }
}