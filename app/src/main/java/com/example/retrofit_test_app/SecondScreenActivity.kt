package com.example.retrofit_test_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofit_test_app.databinding.ActivitySecondScreenBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeUrl: String = intent.extras!!.getString("clipUrl").toString()
        val poster: String = intent.extras!!.getString("poster").toString()
        val title: String = intent.extras!!.getString("title").toString()
        val plot: String = intent.extras!!.getString("plot").toString()
        val genes: String = intent.extras!!.getString("genes").toString()
        val episodes: String = intent.extras!!.getString("episodes").toString()
        val rating: String = intent.extras!!.getString("rating").toString()
        println("Vikash anim : $animeUrl")

        val videoId = extractVideoIdFromUrl(animeUrl)
        println("Vikash id: $videoId")


        lifecycle.addObserver(binding.youtubePreview)

        if (videoId != "") {
            binding.poster.visibility = View.GONE
            binding.youtubePreview.visibility = View.VISIBLE
            binding.youtubePreview.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }
        else if (videoId == "") {
            binding.poster.visibility = View.VISIBLE
            binding.youtubePreview.visibility = View.GONE

            Glide.with(this).load(poster)
                .into(binding.poster)

        }

        binding.title.text = "Title: $title"
        binding.plot.text = "Plot: $plot"
        binding.genre.text = "Genre: $genes"
        binding.episodes.text = "Episod: $episodes"
        binding.rating.text = "Rating: $rating"

    }

    private fun extractVideoIdFromUrl(url: String): String {
        val regex = "v=([^&]*)".toRegex()
        val matchResult = regex.find(url)
        return matchResult?.groupValues?.get(1) ?: ""
    }
}