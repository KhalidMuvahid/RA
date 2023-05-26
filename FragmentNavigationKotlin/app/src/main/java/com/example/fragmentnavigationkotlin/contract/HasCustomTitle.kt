package com.example.fragmentnavigationkotlin.contract

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getTitle():Int

}