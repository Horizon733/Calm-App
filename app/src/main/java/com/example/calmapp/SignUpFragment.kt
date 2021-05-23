package com.example.calmapp

import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.calmapp.databinding.ActivityCalenderBinding
import com.example.calmapp.databinding.ActivityMainBinding
import com.example.calmapp.databinding.FragmentSignUpBinding
import com.google.android.gms.common.internal.Objects
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var fragmentSignUpBinding: FragmentSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_sign_up, container, false)
        fragmentSignUpBinding = FragmentSignUpBinding.bind(view)

        fragmentSignUpBinding.signUpButton.setOnClickListener(View.OnClickListener {
            val username = fragmentSignUpBinding.signUpUsernameField.text.toString()
            val email = fragmentSignUpBinding.signUpEmailField.text.toString()
            val password = fragmentSignUpBinding.signUpPasswordField.text.toString()

            formValidation(username,email,password)
            registerUser(email,password)
//            Toast.makeText(context,email,Toast.LENGTH_SHORT).show()
        })

        return view
    }

    private fun formValidation(userName:String,email: String,password: String){

        if(TextUtils.isEmpty(userName)){
            fragmentSignUpBinding.signUpUsernameField.error = "Enter username"
            return
        }

        if(TextUtils.isEmpty(email)){
            fragmentSignUpBinding.signUpEmailField.error = "Enter Valid Email ID"
            return
        }

        if(TextUtils.isEmpty(password) || password.length < 8){
            fragmentSignUpBinding.signUpPasswordField.error = "Enter Valid Password"
            return
        }

    }

    private fun registerUser(email: String,password: String){
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
            OnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(context,"Sign Up Successfully!",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"failed! ${it.exception.toString()}",Toast.LENGTH_SHORT).show()
                }
            })
    }
}