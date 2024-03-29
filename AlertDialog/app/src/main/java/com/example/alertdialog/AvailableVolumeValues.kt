package com.example.alertdialog

class AvailableVolumeValues (
    val values:List<Int>,
    val currentIndex:Int
){

    companion object{
        fun createVolume(currentValue:Int):AvailableVolumeValues{
            val values:IntProgression = (0..100 step 10)
            val currentIndex = values.indexOf(currentValue)
            return if (currentIndex == -1){
                val list = values + currentValue
                AvailableVolumeValues(list,list.lastIndex)
            }else{
                AvailableVolumeValues(values.toList(),currentIndex)
            }
        }
    }
}
