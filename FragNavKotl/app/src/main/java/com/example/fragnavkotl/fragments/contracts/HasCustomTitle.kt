package com.example.fragnavkotl.fragments.contracts

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getTitleRes():Int
}