package com.example.calmapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
        val id = currentSong.guid.split(":")[2]
        holder.song_parent.setOnClickListener {
            val youtube_intent_app = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+id))
            val youtube_intent_web = Intent(Intent.ACTION_VIEW, Uri.parse(currentSong.link))
            try{
                context.startActivity(youtube_intent_app)
            }catch (e:ActivityNotFoundException){
                context.startActivity(youtube_intent_web)
            }
        }

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class ItemsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titleTv = view.findViewById<TextView>(R.id.song_title)
        val thumbnail = view.findViewById<ImageView>(R.id.song_thumbnail)
        val author = view.findViewById<TextView>(R.id.author)
        val song_parent = view.findViewById<LinearLayout>(R.id.song_parent)
    }
}
