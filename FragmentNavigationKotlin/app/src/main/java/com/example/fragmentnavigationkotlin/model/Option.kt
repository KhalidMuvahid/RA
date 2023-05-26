package com.example.fragmentnavigationkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Option(val box:Int,val timeEnable:Boolean):Parcelable{

    companion object{
        val DEFAULT:Option = Option(box=3,timeEnable = false)
    }
}