package com.example.calmapp


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.calmapp.databinding.FragmentHomeBinding
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment(), OnChartValueSelectedListener {

    private lateinit var homeFragmentBinding: FragmentHomeBinding
    private lateinit var itemList: ArrayList<SongsFeed.Feed>
    private lateinit var  songsListAdapter:SongsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentBinding = FragmentHomeBinding.inflate(layoutInflater)
        homeFragmentBinding.dailyProgress.setOnChartValueSelectedListener(this)
        itemList = ArrayList<SongsFeed.Feed>()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getAllsongs()
//        Log.e("song title", itemList[0].title)


        homeFragmentBinding.dailyProgress.getDescription().setEnabled(false)

        homeFragmentBinding.dailyProgress.setTouchEnabled(true)

        homeFragmentBinding.dailyProgress.setDragDecelerationFrictionCoef(0.9f)

        homeFragmentBinding.dailyProgress.setDragEnabled(true)
        homeFragmentBinding.dailyProgress.setScaleEnabled(true)
        homeFragmentBinding.dailyProgress.setDrawGridBackground(false)
        homeFragmentBinding.dailyProgress.setHighlightPerDragEnabled(false)

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

    fun getAllsongs(){
        val url = "https://api.rss2json.com/v1/"
        val okHttpClient = OkHttpClient()
        val retrofit = Retrofit
            .Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val feedFetcher = retrofit.create(FeedFetcher::class.java)
        val response = feedFetcher.feedFetecher("https://www.youtube.com/feeds/videos.xml?channel_id=UCb_kshGodseYhLPcDtxWv5w")
        response.enqueue(object : Callback<SongsFeed>{
            override fun onResponse(
                call: Call<SongsFeed>,
                response: Response<SongsFeed>
            ) {
                Log.e("respomse","${response}")
                if(response.body()!=null){
                    val items = response.body()
                    if (items != null) {
                        itemList = items.items
                        songsListAdapter = SongsListAdapter(context!!,itemList)
                        homeFragmentBinding.songsList.adapter = songsListAdapter

                        val linearLayoutManager = LinearLayoutManager(context)
                        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                        homeFragmentBinding.songsList.layoutManager = linearLayoutManager
                    }
                }
            }

            override fun onFailure(call: Call<SongsFeed>, t: Throwable) {
               Log.e("error while fetching","${t.message}")
            }

        })
    }

}