package com.example.calmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.calmapp.databinding.ActivityUserProfileBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import com.google.firebase.database.ValueEventListener as ValueEventListener1

class UserProfileActivity : AppCompatActivity() {
    private lateinit var userProfileBinding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userProfileBinding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(userProfileBinding.root)
        setSupportActionBar(userProfileBinding.userProfileToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val userName: String = userProfileBinding.usernameEtTi.editText?.text.toString()
        val age: String = userProfileBinding.ageEtTi.editText?.text.toString()
        val dateOfBirth: String = userProfileBinding.dobEtTi.editText?.text.toString()

        fetchUserName()
        updateDb(userName,age,dateOfBirth)
    }

    private fun fetchUserName(){
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.currentUser?.uid.toString())
        myRef.addListenerForSingleValueEvent(object : EventListener,
            com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userName: String = snapshot.child("username").value.toString()
                userProfileBinding.usernameTv.text = userName
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    private fun updateDb(name:String, age:String, dob: String){
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.currentUser?.uid.toString())
        myRef.child("name").setValue(name)
        myRef.child("age").setValue(age)
        myRef.child("name").setValue(dob)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val optionsMenu = menuInflater
        optionsMenu.inflate(R.menu.user_profile_menu, menu)

        return true

    }
}