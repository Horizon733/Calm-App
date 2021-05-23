package com.example.calmapp

import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    //private lateinit var activitySettingsBinding:ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        auth = Firebase.auth
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar?>(R.id.settings_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val switch = findViewById<SwitchCompat>(R.id.switch_dark_mode)
        val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        switch.isChecked =  when(isNightTheme){
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
        switch.setOnCheckedChangeListener { _, checked ->
            darkModeToggler(isNightTheme)
        }
        findViewById<Button>(R.id.logout_button).setOnClickListener {
            val user = auth.currentUser.email.toString()
            val greeting = "Logged out " + user.subSequence(0, pos(user, '@' )).toString() + " successfully !"
            Toast.makeText(this, greeting , Toast.LENGTH_SHORT).show()
            auth.signOut()
            finish()
            startActivity(Intent(this, HomeActivity::class.java))

        }

    }
    fun isEmailInvalid(email: String) : Boolean{
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun pos(str:String, char:Char): Int {
        if(str.isEmpty())
            return -1
        for(i in 0 .. str.length){
            if (str[i] == char)
                return i
        }
        return -1
    }



    fun darkModeToggler(isNightTheme: Int){
            val desc: String;
        when (isNightTheme) {

            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                overridePendingTransition(0, 0)
                overridePendingTransition(0, 0)
                desc = "Dark Mode Disabled!"
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                overridePendingTransition(0, 0)
                overridePendingTransition(0, 0)
                desc = "Dark Mode Enabled!"
            }
        }
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show()


    }


}