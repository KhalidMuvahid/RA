package com.example.fragnavkotl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.example.fragmentnavigationkotlin.fragment.AboutFragment
import com.example.fragmentnavigationkotlin.fragment.CongratulationFragment
import com.example.fragnavkotl.fragments.BoxFragment
import com.example.fragnavkotl.databinding.ActivityMainBinding
import com.example.fragnavkotl.fragments.MenuFragment
import com.example.fragnavkotl.fragments.OptionFragment
import com.example.fragnavkotl.fragments.contracts.HasCustomTitle
import com.example.fragnavkotl.fragments.contracts.Navigator
import com.example.fragnavkotl.fragments.contracts.ResultListener
import com.example.fragnavkotl.model.Option

class MainActivity : AppCompatActivity(),Navigator {
    lateinit var binding: ActivityMainBinding

    private val currentFragment:Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    private val fragmentCallbackListener = object : FragmentManager.FragmentLifecycleCallbacks(){
        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentCreated(fm, f, savedInstanceState)
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setSupportActionBar(binding.toolBar)

        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer,MenuFragment())
                .commit()
        }


        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbackListener,false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentCallbackListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed();
        return true
    }



    override fun showBoxSelectionScreen(option: Option) {
       launchFragment(BoxFragment.newInstance(option))
    }


    override fun showOptionScreen(option: Option) {
        launchFragment(OptionFragment.newInstance(option))
    }

    override fun showAboutScreen() {
        launchFragment(AboutFragment())
    }

    override fun showCongratulationsScreen() {
        launchFragment(CongratulationFragment())
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun <T : Parcelable> publishResult(result: T) {
        TODO("Not yet implemented")
    }

    override fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
        TODO("Not yet implemented")
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer,fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        updateUI()
        return true
    }

    private fun updateUI() {

        val fragment = currentFragment

        if (fragment is HasCustomTitle){
            binding.toolBar.title = getString(fragment.getTitleRes())
        }else{
            binding.toolBar.title = getString(R.string.app_name)
        }
    }

}