package com.example.retrofit_test_app

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_test_app.data.model.Data
import com.example.retrofit_test_app.databinding.AnimeItemLayoutBinding

class AnimeListAdapter(private val context: MainActivity, private val animeList: List<Data>) :
    RecyclerView.Adapter<AnimeListAdapter.DataViewHolder>() {

    class DataViewHolder(val binding: AnimeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = AnimeItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = animeList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val animeListData = animeList[position]
        val poster = animeListData.images.jpg.image_url
        val title = animeListData.title
        val plot = animeListData.synopsis
        val genes = animeListData.genres.joinToString(", ") { it.name }
        val episodes = animeListData.episodes
        val rating = animeListData.rating


        holder.binding.title.text = "Title: ${animeListData.title}"
        holder.binding.episodes.text = "Number of Episode: ${animeListData.episodes}"
        println("Rating ${animeListData.rating}")
        holder.binding.rating.text = "Rating: ${animeListData.rating}"




        Glide.with(context).load(poster)
            .into(holder.binding.imageViewBanner)

        holder.itemView.setOnClickListener {
            val animeUrl = animeListData.trailer.url
            val i = Intent(context, SecondScreenActivity::class.java)
            i.putExtra("clipUrl", animeUrl)
            i.putExtra("poster", poster)
            i.putExtra("title", title)
            i.putExtra("plot", plot)
            i.putExtra("genes", genes)
            i.putExtra("episodes", episodes)
            i.putExtra("rating", rating)
            context.startActivity(i)
        }
    }
}


