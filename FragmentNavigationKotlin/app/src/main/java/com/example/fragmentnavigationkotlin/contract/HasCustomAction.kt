package com.example.fragmentnavigationkotlin.contract

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface HasCustomAction {
    fun getCustomAction():CustomAction
}

class CustomAction(@DrawableRes val icon:Int,
                   @StringRes val iconTitle:Int,
                   val onAction:Runnable)