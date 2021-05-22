package com.example.calmapp


import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.calmapp.databinding.FragmentHomeBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate


class HomeFragment : Fragment(), OnChartValueSelectedListener {

    private lateinit var homeFragmentBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentBinding = FragmentHomeBinding.inflate(layoutInflater)
        homeFragmentBinding.dailyProgress.setOnChartValueSelectedListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // no description text

        // no description text
        homeFragmentBinding.dailyProgress.getDescription().setEnabled(false)

        // enable touch gestures

        // enable touch gestures
        homeFragmentBinding.dailyProgress.setTouchEnabled(true)

        homeFragmentBinding.dailyProgress.setDragDecelerationFrictionCoef(0.9f)

        // enable scaling and dragging

        // enable scaling and dragging
        homeFragmentBinding.dailyProgress.setDragEnabled(true)
        homeFragmentBinding.dailyProgress.setScaleEnabled(true)
        homeFragmentBinding.dailyProgress.setDrawGridBackground(false)
        homeFragmentBinding.dailyProgress.setHighlightPerDragEnabled(false)

        // if disabled, scaling can be done on x- and y-axis separately

        // if disabled, scaling can be done on x- and y-axis separately
        homeFragmentBinding.dailyProgress.setPinchZoom(true)


        homeFragmentBinding.dailyProgress.animateX(1500)


        homeFragmentBinding.dailyProgress.xAxis.isEnabled = false
        homeFragmentBinding.dailyProgress.axisRight.isEnabled=false
        homeFragmentBinding.dailyProgress.axisLeft.isEnabled = false

        setData(10,100F)

        return homeFragmentBinding.root
    }

    private fun setData(count: Int, range: Float) {
        val values1: ArrayList<Entry> = ArrayList()
        for (i in 0 until count) {
            val element = (Math.random() * (range / 2f)).toFloat() + 50
            values1.add(Entry(i.toFloat(), element))
        }

        val set1: LineDataSet


        if (homeFragmentBinding.dailyProgress.getData() != null &&
            homeFragmentBinding.dailyProgress.getData().getDataSetCount() > 0
        ) {
            set1 = homeFragmentBinding.dailyProgress.getData().getDataSetByIndex(0) as LineDataSet
            set1.setValues(values1)
            homeFragmentBinding.dailyProgress.getData().notifyDataChanged()
            homeFragmentBinding.dailyProgress.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values1, "Your Progress")
            set1.axisDependency = AxisDependency.LEFT
            set1.color = R.color.orange
            set1.setCircleColor(R.color.orange)
            set1.lineWidth = 2f
            set1.circleRadius = 3f
            set1.fillAlpha = 65
            set1.fillColor = R.color.orange
            set1.setDrawCircleHole(false)
            set1.setColors(intArrayOf(R.color.cream_700),context)
            set1.fillDrawable = ContextCompat.getDrawable(context!!,R.drawable.gradient)
            set1.setDrawFilled(true)

            val data = LineData(set1)
            data.setValueTextColor(Color.BLACK)
            data.setValueTextSize(9f)


            // set data
            homeFragmentBinding.dailyProgress.setData(data)
        }

    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        if (e != null) {
            homeFragmentBinding.dailyProgress.centerViewToAnimated(e.getX(), e.getY(),
                h?.getDataSetIndex()?.let {
                    homeFragmentBinding.dailyProgress.getData().getDataSetByIndex(it)
                        .getAxisDependency()
                }, 500)
        };
    }

    override fun onNothingSelected() {

    }

}