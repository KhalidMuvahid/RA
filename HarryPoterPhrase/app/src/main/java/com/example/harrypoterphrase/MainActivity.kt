package com.example.harrypoterphrase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.javafaker.Faker

class MainActivity : AppCompatActivity() {

    private val faker =Faker.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null){
            val fragment = CounterFragment.newInstance(1,createQuote())
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        }
    }

    fun createQuote(): String {
        return faker.harryPotter().quote()
    }

    fun getScreenCount():Int{
        return supportFragmentManager.backStackEntryCount + 1
    }
}