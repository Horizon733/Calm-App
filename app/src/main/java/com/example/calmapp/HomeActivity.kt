package com.example.calmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.calmapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottomToTop: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_to_top_anim) }
    private val fromTopToBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_top_to_bottom_anim) }
    private lateinit var homeBinding: ActivityHomeBinding
    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        homeBinding.moreFab.setOnClickListener {
            onMenuButtonClicked()
        }
        homeBinding.homeFab.setOnClickListener {
            supportFragmentManager.beginTransaction().add(homeBinding.homeContainer.id,HomeFragment(),"Home").commit()
        }
        homeBinding.calendarFab.setOnClickListener {
            supportFragmentManager.beginTransaction().add(homeBinding.homeContainer.id,HomeFragment(),"Calendar").commit()
        }
        homeBinding.settingsFab.setOnClickListener {
            supportFragmentManager.beginTransaction().add(homeBinding.homeContainer.id,HomeFragment(),"Settings").commit()
        }
    }

    private fun onMenuButtonClicked() {
        setAnimation(clicked)
        setVisibility(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked){
            homeBinding.settingsFab.startAnimation(fromBottomToTop)
            homeBinding.homeFab.startAnimation(fromBottomToTop)
            homeBinding.calendarFab.startAnimation(fromBottomToTop)
            homeBinding.moreFab.startAnimation(rotateOpen)
            homeBinding.moreFab.setImageResource(R.drawable.ic_baseline_close_24)
        }else{
            homeBinding.settingsFab.startAnimation(fromTopToBottom)
            homeBinding.homeFab.startAnimation(fromTopToBottom)
            homeBinding.calendarFab.startAnimation(fromTopToBottom)
            homeBinding.moreFab.startAnimation(rotateClose)
            homeBinding.moreFab.setImageResource(R.drawable.ic_more)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked){
            homeBinding.settingsFab.visibility = View.VISIBLE
            homeBinding.homeFab.visibility = View.VISIBLE
            homeBinding.calendarFab.visibility = View.VISIBLE

        }else{
            homeBinding.settingsFab.visibility = View.INVISIBLE
            homeBinding.homeFab.visibility = View.INVISIBLE
            homeBinding.calendarFab.visibility = View.INVISIBLE
        }
    }
}