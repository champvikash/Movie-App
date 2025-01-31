package com.example.retrofit_test_app.ui.secondscreen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofit_test_app.databinding.ActivitySecondScreenBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var videoId: String
    private var isVideoLoaded = false
    private val viewModel: SecondScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeUrl = intent.extras!!.getString("clipUrl").toString()
        val poster = intent.extras!!.getString("poster").toString()
        val title = intent.extras!!.getString("title").toString()
        val plot = intent.extras!!.getString("plot").toString()
        val genes = intent.extras!!.getString("genes").toString()
        val episodes = intent.extras!!.getString("episodes").toString()
        val rating = intent.extras!!.getString("rating").toString()
        println("Vikash anim : $animeUrl")

        videoId = extractVideoIdFromUrl(animeUrl)
        println("Vikash id: $videoId")


        lifecycle.addObserver(binding.youtubePreview)

        if (videoId.isNotEmpty()) {
            binding.poster.visibility = View.GONE
            binding.youtubePreview.visibility = View.VISIBLE

            binding.youtubePreview.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    viewModel.playbackTime.observe(this@SecondScreenActivity) { savedTime ->
                        if (!isVideoLoaded) {
                            if (savedTime > 0f) {
                                youTubePlayer.loadVideo(videoId, savedTime)
                            } else {
                                youTubePlayer.cueVideo(videoId, 0f)
                            }
                            isVideoLoaded = true
                        }
                    }


                    youTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                        override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                            viewModel.savePlaybackTime(second)
                        }
                    })
                }
            })
        } else {
            binding.poster.visibility = View.VISIBLE
            binding.youtubePreview.visibility = View.GONE
            Glide.with(this).load(poster).into(binding.poster)
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