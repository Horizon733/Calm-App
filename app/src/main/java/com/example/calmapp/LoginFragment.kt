package com.example.calmapp

import android.content.res.Configuration
import android.media.Image
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.calmapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var fragmentLoginBinding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_login, container, false)

        fragmentLoginBinding = FragmentLoginBinding.bind(view)

        fragmentLoginBinding.loginPasswordField.setOnClickListener(View.OnClickListener {
            val email = fragmentLoginBinding.loginEmailField.text.toString()
            val password = fragmentLoginBinding.loginPasswordField.text.toString()
            formValidation(email,password)
        })



        val darkMode: ImageView = view.findViewById(R.id.login_light_mode_button)
        darkMode.setOnClickListener(View.OnClickListener {
            if(darkMode.isSelected){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                darkMode.isSelected = false
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                darkMode.isSelected = true
            }

        })

        /*val darkMode: ImageView = view.findViewById(R.id.login_light_mode_button)
        darkMode.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"Ligth Mode",Toast.LENGTH_SHORT).show()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        })*/

        return view
    }

    private fun formValidation(email:String,password:String){
        if(TextUtils.isEmpty(email)){
            fragmentLoginBinding.loginEmailField.error = "Enter email"
            return
        }

        if(TextUtils.isEmpty(password)){
            fragmentLoginBinding.loginPasswordField.error = "Enter Valid Password"
            return
        }
    }


}