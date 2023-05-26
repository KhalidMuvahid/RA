package com.example.fragmentnavigationkotlin.contract

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.fragmentnavigationkotlin.model.Option

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator():Navigator{
    return requireActivity() as Navigator
}

interface Navigator {

    fun showBoxSelectScreen(option:Option)

    fun showOptionScreen(option: Option)

    fun showAboutScreen()

    fun  goBack()

    fun goToMenu()

    fun <T:Parcelable> publishResult(result:T)

    fun <T:Parcelable> listenerResult(clazz:Class<T>,other:LifecycleOwner,listener: ResultListener<T>)


}