package com.example.calmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.calmapp.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var userProfileBinding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userProfileBinding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(userProfileBinding.root)
        setSupportActionBar(userProfileBinding.userProfileToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val optionsMenu = menuInflater
        optionsMenu.inflate(R.menu.user_profile_menu, menu)

        return true

    }
}