package com.example.fragnavkotl.fragments.contracts

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.fragnavkotl.model.Option

typealias ResultListener<T> = (T)->Unit;

fun Fragment.navigator():Navigator{
    return requireActivity() as Navigator
}

interface Navigator {

    fun showBoxSelectionScreen(option: Option)

    fun showOptionScreen(option: Option)

    fun showAboutScreen()

    fun showCongratulationsScreen()

    fun goBack()

    fun goToMenu()

    fun <T:Parcelable> publishResult(result:T)

    fun <T:Parcelable> listenResult(clazz: Class<T>,owner: LifecycleOwner,listener:ResultListener<T>)




}