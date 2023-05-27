package com.example.fragnavkotl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragnavkotl.fragments.MenuFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,MenuFragment()).commit()
    }


}