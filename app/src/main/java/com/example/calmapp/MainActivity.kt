package com.example.calmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager.beginTransaction().add(R.id.welcome_screen_container,WelcomeScreen(),"Home").commit()
//        supportFragmentManager.beginTransaction().add(R.id.welcome_screen_container,SignUpFragment(),"Home").commit()
        supportFragmentManager.beginTransaction().add(R.id.welcome_screen_container,LoginFragment(),"Home").commit()
    }
}