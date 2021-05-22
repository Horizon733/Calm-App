package com.example.calmapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = findViewById<FrameLayout>(R.id.fragment)
        supportFragmentManager.beginTransaction().add(fragment.id,SettingsFragment(),"Settings").commit()


    }
}