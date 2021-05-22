package com.example.calmapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SongsListAdapter(var context: Context, var itemsList:List<SongsFeed.Feed>) : RecyclerView.Adapter<SongsListAdapter.ItemsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.song_item_list, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val currentSong = itemsList[position]
        holder.titleTv.setText(currentSong.title.toLowerCase())
        Glide.with(context).load(currentSong.thumbnail).into(holder.thumbnail)
        holder.author.setText(currentSong.author)

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class ItemsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titleTv = view.findViewById<TextView>(R.id.song_title)
        val thumbnail = view.findViewById<ImageView>(R.id.song_thumbnail)
        val author = view.findViewById<TextView>(R.id.author)
    }
}
