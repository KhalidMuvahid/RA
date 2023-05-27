package com.example.fragnavkotl.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Option(val box:Int,val timeEnable:Boolean) : Parcelable {

    companion object{
        val DEFAULT = Option(3,false);
    }
}