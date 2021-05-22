package com.example.calmapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CalenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        val calenderView: CalendarView = findViewById(R.id.calendarView)
        val date: TextView = findViewById(R.id.cal_bs_date)
        date.text = calenderView.date.toString()


        val constraintLayout: ConstraintLayout = findViewById(R.id.calender_bottom_sheet_view)
        val bottomsheetBehaviout = BottomSheetBehavior.from(constraintLayout)
        bottomsheetBehaviout.state = BottomSheetBehavior.STATE_EXPANDED

    }
}