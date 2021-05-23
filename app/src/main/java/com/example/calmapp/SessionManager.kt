package com.example.calmapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context?) {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var context: Context
    private val PRIVATE_MODE: Int = 0
    private val PREF_NAME: String = "MySession"
    private val KEY_UID: String = "UserID"
    private val KEY_DARKMODE: String = "isDarkMode"
    private val KEY_USERNAME: String = "Username"

    init {
        this.context = context!!
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = sharedPreferences.edit()
    }

    fun createLoginSession(userId: String, darkMode: Boolean){
        editor.putString(KEY_UID,userId)
//        editor.putString(KEY_USERNAME,username)
        editor.putBoolean(KEY_DARKMODE,darkMode)
    }

    /*fun getUsername(): String? {
        return sharedPreferences.getString(KEY_USERNAME,null)
    }*/

    fun getIsDarkMode(): Boolean{
        return sharedPreferences.getBoolean(KEY_DARKMODE,true)
    }

    fun getUserID(): String? {
        return sharedPreferences.getString(KEY_UID,null)
    }
}