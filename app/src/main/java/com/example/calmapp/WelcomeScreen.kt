package com.example.calmapp

import android.app.Activity
import android.os.Bundle
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager

class WelcomeScreen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_welcome_screen, container, false)

        val goToLogin: Button = view.findViewById(R.id.go_to_login_button)
        goToLogin.setOnClickListener(View.OnClickListener {
//            val fragmentManager
//            supportFragmentManager.beginTransaction().replace(R.id.welcome_screen_container,WelcomeScreen(),"Home").commit()
        })

        return view
    }
}