package com.example.calmapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class SongsFeed(var status:String ,@SerializedName("items") @Expose var items:ArrayList<Feed>) {

    class Feed(var title:String, var author:String,var thumbnail:String,var link:String){

    }
}
