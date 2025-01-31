package com.example.retrofit_test_app.ui.homescreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofit_test_app.ui.secondscreen.SecondScreenActivity
import com.example.retrofit_test_app.databinding.ActivityMainBinding
import com.example.retrofit_test_app.data.repository.Repository

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(Repository()) }
    private lateinit var animeAdapter: AnimeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        viewModel.getPost()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        animeAdapter = AnimeListAdapter(this, emptyList(), this)
        binding.recyclerView.adapter = animeAdapter
    }

    private fun observeViewModel() {
        viewModel.myResponse.observe(this) { animList ->
            animeAdapter.updateData(animList)
            animList.forEach { anime ->
                Log.d("Anime Title", anime.title)
            }
        }
    }

    override fun onItemViewClick(
        clipUrl: String,
        poster: String,
        title: String,
        plot: String,
        genes: String,
        episodes: String,
        rating: String
    ) {
        val intent = Intent(this, SecondScreenActivity::class.java).apply {
            putExtra("clipUrl", clipUrl)
            putExtra("poster", poster)
            putExtra("title", title)
            putExtra("plot", plot)
            putExtra("genes", genes)
            putExtra("episodes", episodes)
            putExtra("rating", rating)
        }
        startActivity(intent)
    }
}
