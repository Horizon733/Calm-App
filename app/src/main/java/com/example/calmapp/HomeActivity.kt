package com.example.calmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
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
        Glide.with(this)
            .load("https://images-ext-1.discordapp.net/external/N9yFjDEUQlLVZZwnUnnJjdfZtsc9E4etR3x5luVKhbs/%3Fauthuser%3D0/https/lh3.googleusercontent.com/jUoPnpFQ2D3nscVH9jePBR3VLakUgzJLHiAtVBmO-e846BjUxdjUxu7XyhjO5X53SXgBlpt8Maq9xtjB3w1S3NjLTU-2xRbyP7DWNOym_p54GPNBYK_QPsqniypnMzoGcq6nBAkhmLoQoD59odifbyogO_bIPYWMF46BtvHmpvIcgK4or4GVzIiAMvbkwVYeXZtBnuyLmvqT2fWWrhIt7S853eNN8ERCdp_q2fj7ON6kHjMnxNxAmLHk1UyFICkY9uwDnzqHKSgA1DBJMFykIbdKXhxwP2iVC3qRPUc-D4W7ypaAES0xGGM1ZFXZCHN2LUrE3cb8Mz3AW6JvHieQPesnNpy78i-COopHVkmnrrhDCQDfOsEbiPccW4pibBz7dS_BNRPUdVOeRa0h2mSwfmrCZHd1z4bfTawHayyG0TKXfX5l98PwqmY7ohMkhGJWj7st466GlBG8pCnRgm9cNNMeMHlGwzAVGmUfpuIN8TBbBLTZObZbDeURImEoIeiKH7YIjfdWqhW5fGRODO5VJqJ3ndpsfFhhQtPJ0zj38MH8zH6jVfylhRrY4dpL8qfPjorOoFivuZYBIgvwCsJi2ixLNVQ9Hir_9ZyfaXBJNLNrDBBRYfB9P8EVcUX3rL7710xzoPobjbQG-rRZvh_nGjZkGS8nr1TlAHUcYdSDDXLWf5Uv9TYxYsiNRuHnoQMFXkr6gWvqPB3BD_q3la1Xm1Q4wg%3Ds500-no?width=497&height=497")
            .into(homeBinding.profileImg)
        homeBinding.profileName.setText("Hiten Chawda")

        supportFragmentManager.beginTransaction().replace(homeBinding.homeContainer.id,HomeFragment(),"Home").commit()
        homeBinding.moreFab.setOnClickListener {
            onMenuButtonClicked()
        }
        homeBinding.homeFab.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(homeBinding.homeContainer.id,HomeFragment(),"Home").commit()
        }
        homeBinding.calendarFab.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(homeBinding.homeContainer.id,CalendarFragment(),"Calendar").commit()
        }
        homeBinding.settingsFab.setOnClickListener {
            startActivity(Intent(applicationContext,SettingsActivity::class.java))
        }
        homeBinding.toolbarHome.setOnClickListener {
            val profileIntent = Intent(this,UserProfileActivity::class.java)
            startActivity(profileIntent)
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