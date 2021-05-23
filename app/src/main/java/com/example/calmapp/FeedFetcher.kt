package com.example.calmapp

import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface FeedFetcher {
    @GET("api.json")
    fun feedFetecher(@Query("rss_url") rss_url:String): Call<SongsFeed>
}