package com.example.calmapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import kotlin.coroutines.coroutineContext

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val switch = findViewById<SwitchCompat>(R.id.switch_dark_mode)
        val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        switch.isChecked =  when(isNightTheme){
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
        switch.setOnCheckedChangeListener { _, checked ->
            darkModeToggler(isNightTheme)
        }

    }


    fun darkModeToggler(isNightTheme: Int){
            val desc: String;


        when (isNightTheme) {

            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                overridePendingTransition(0, 0)
                overridePendingTransition(0, 0)
                desc = "Dark Mode Disabled!"
                finish()
                startActivity(Intent(this, SettingsFragment::class.java))
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                overridePendingTransition(0, 0)
                overridePendingTransition(0, 0)
                desc = "Dark Mode Enabled!"
            }
        }
        finish()
        startActivity(Intent(this, SettingsFragment::class.java))
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show()


    }


}