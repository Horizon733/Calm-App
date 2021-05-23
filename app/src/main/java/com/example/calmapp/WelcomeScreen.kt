package com.example.calmapp

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth

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

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        if(mAuth.currentUser != null){
            startActivity(Intent(context,HomeActivity::class.java))
        }

        val goToLogin: Button = view.findViewById(R.id.go_to_login_button)
        goToLogin.setOnClickListener(View.OnClickListener {
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.welcome_screen_container,LoginFragment()).commit()
        })

        val goToSignUp: TextView = view.findViewById(R.id.welcome_page_signup)
        goToSignUp.setOnClickListener(View.OnClickListener {
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.welcome_screen_container,SignUpFragment()).commit()
        })

        return view
    }
}