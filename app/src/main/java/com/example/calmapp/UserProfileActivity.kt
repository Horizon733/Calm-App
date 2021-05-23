package com.example.calmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.calmapp.databinding.ActivityUserProfileBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class UserProfileActivity : AppCompatActivity() {
    private lateinit var userProfileBinding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userProfileBinding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(userProfileBinding.root)
        setSupportActionBar(userProfileBinding.userProfileToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val link:String = "https://lh3.googleusercontent.com/_tgr3YG8pFB5JaUEc_HYwRT6F4T1DcqRRp5F08GFibWRDZ15IDQPwswRhS-CW0lDX33r46TDaRNJrqrQKbfHknikHECWKLuqVC8zw_yIy89_Kzd1Lx549Jm8FXujEBoSlp3MHFHer8DN6z63-tQzCD3IF9h8dL0MW-SaP7m00-G5fww7bNCTQauX3gz3DvjcPLVv_oB5cjiUpXMA2hnmb5OSopWqZ7ps9JJZtphd4K-fGhJ7aP2iManTdpEo_2-DFZGBGjdyw1K8mPinJtTCe4sxPuDiBURWKqtPMzYxzqYRrhmlZvmn8MYwyp7ldKn5t_IFipE5p8GDIFeF8qL9s6rXM3hVNT1jGijiLW5PVd5fSWVRIP_Y1TiW0P_dLI1DsXknVtRlulwbyBKxCEPCVtB_iFmn30kaangecgXGO6x4XfThJ-pWRwmZDiXV2bpngFmBYbT1zZAMoLTnLtFUc2eOziGBpeYkoY8M1jgHY3QkvOcuHvRQ7ES9t6J6aD-XCYp_aOyDsRepjU4fW9u5javF8mG5byrznwx-5RwPIBtRJDGN2cThIREYYSZY2mkuXBMyzHR6VBzk0RbawKC3GXzG2Cuq00re_4-okV5CEIjM3H0vThETNUgfmyj69HAlI7VezgXbdonZgcFlitHo8WMqRlhjuDWB3_mhqrqjLMbqhvHDklPtZYqvA-IE0dvfFKGa7O63UNa2gMliA2cbOujuTg=s500-no?authuser=0"

        Glide.with(this)
            .load(link)
            .into(userProfileBinding.userProfileImg)



        fetchUserName()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save -> {
                val userName: String = userProfileBinding.usernameEtTi.editText?.text.toString()
                val age: String = userProfileBinding.ageEtTi.editText?.text.toString()
                val dateOfBirth: String = userProfileBinding.dobEtTi.editText?.text.toString()
                updateDb(userName,age,dateOfBirth)
                Toast.makeText(this,"Changes saved!",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,HomeActivity::class.java))
            }
            R.id.close -> {
                startActivity(Intent(this,HomeActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun fetchUserName(){
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.currentUser?.uid.toString())
        myRef.addListenerForSingleValueEvent(object : EventListener,
            com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.child("age").exists()){
                    val userName: String = snapshot.child("username").value.toString()
                    userProfileBinding.usernameTv.text = userName
                    val age: String = snapshot.child("age").value.toString()
                    val dob: String = snapshot.child("name").value.toString()
                    userProfileBinding.usernameEt.setText(userName)
                    userProfileBinding.ageEt.setText(age)
                    userProfileBinding.dobEt.setText(dob)
                }
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