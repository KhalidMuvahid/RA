package com.example.alertdialog

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alertdialog.databinding.ActivityMainBinding
import kotlin.properties.Delegates

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var volume by Delegates.notNull<Int>()
    private var color by Delegates.notNull<Int>()

    private val KEY_VOLUME = "volume"
    private val KEY_COLOR = "color"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volume = savedInstanceState?.getInt(KEY_VOLUME) ?: 50
        color = savedInstanceState?.getInt(KEY_COLOR) ?: Color.RED

        binding.defaultDialogBt.setOnClickListener { createDefaultDialog() }
        binding.singleChoiceDialogBt.setOnClickListener { createSingleChoiceDialog() }
        binding.singChoiceConfDialogBt.setOnClickListener { createSingleChoiceConfirmDialog() }
        binding.multipleChoiceDialogBt.setOnClickListener { multipleChoiceDialog() }
        binding.multipleChoiceConfirmDialogBt.setOnClickListener { multipleChoiceConfirmDialog() }

        updateUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_VOLUME,volume)
        outState.putInt(KEY_COLOR,color)
    }

    private fun createDefaultDialog() {
        val listener = DialogInterface.OnClickListener { _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> showToast("Android was deleted")
                DialogInterface.BUTTON_NEGATIVE -> showToast("NO no")
                DialogInterface.BUTTON_NEUTRAL -> showToast("Ignore")
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle("Alert Dialog Title")
            .setMessage("Would you like to uninstall android")
            .setPositiveButton("Yes",listener)
            .setNegativeButton("No",listener)
            .setNeutralButton("Ignore",listener)
            .setOnCancelListener {
                showToast("this will be show only when cancelable(true)")
            }
            .setOnDismissListener{
                Log.d(TAG,"dismiss")
            }
            .create()

        dialog.show()
    }

    private fun createSingleChoiceDialog() {
        val volumeItems = AvailableVolumeValues.createVolume(volume)
        val volumeTextItems = volumeItems.values.map { getString(R.string.current_volume,it) }.toTypedArray()

        val dialog = AlertDialog.Builder(this)
            .setTitle("Chose volume")
            .setSingleChoiceItems(volumeTextItems,volumeItems.currentIndex){dialog,witch ->
                volume = volumeItems.values[witch]
                updateUI()
                dialog.dismiss()
            }.create()
        dialog.show()
    }

    private fun createSingleChoiceConfirmDialog() {
        val volumeItems = AvailableVolumeValues.createVolume(volume)
        val volumeTextItems = volumeItems.values.map { getString(R.string.current_volume,it) }.toTypedArray()

        val dialog = AlertDialog.Builder(this)
            .setTitle("Chose volume")
            .setSingleChoiceItems(volumeTextItems,volumeItems.currentIndex,null)
            .setPositiveButton(getString(R.string.confirm)){dialog,_->
                val index = (dialog as AlertDialog).listView.checkedItemPosition
                volume = volumeItems.values[index]
                updateUI()
            }
            .create()
        dialog.show()
    }



    private fun multipleChoiceDialog() {

        val colorItems = resources.getStringArray(R.array.colors)

        val colorComponents = mutableListOf(
            Color.red(this.color),
            Color.green(this.color),
            Color.blue(this.color)
        )

        val checkboxes = colorComponents
            .map { it > 0 }
            .toBooleanArray()

        val dialog = AlertDialog.Builder(this)
            .setTitle("Choose Color")
            .setMultiChoiceItems(colorItems,checkboxes){_,witch,isChecked ->
                colorComponents[witch] = if (isChecked) 255 else 0
                this.color = Color.rgb(
                    colorComponents[0],
                    colorComponents[1],
                    colorComponents[2]
                )
                updateUI()
            }
            .create()
        dialog.show()
    }



    private fun multipleChoiceConfirmDialog() {
        val colorItems = resources.getStringArray(R.array.colors)
        var color = this.color

        val colorComponents = mutableListOf(
            Color.red(this.color),
            Color.green(this.color),
            Color.blue(this.color)
        )

        val checkboxes = colorComponents
            .map { it > 0 }
            .toBooleanArray()

        val dialog = AlertDialog.Builder(this)
            .setTitle("Choose Color")
            .setMultiChoiceItems(colorItems,checkboxes){_,witch,isChecked ->
                colorComponents[witch] = if (isChecked) 255 else 0
                color = Color.rgb(
                    colorComponents[0],
                    colorComponents[1],
                    colorComponents[2]
                )
            }
            .setPositiveButton("Confirm"){_,_ ->
                this.color = color
                updateUI()
            }
            .create()
        dialog.show()
    }



    private fun showToast(toastText: String) {
        Toast.makeText(this,toastText,Toast.LENGTH_SHORT).show()
    }


    private fun updateUI() {
        binding.currentVolumeText.text = getString(R.string.current_volume,volume)
        binding.color.setBackgroundColor(color)
    }

}