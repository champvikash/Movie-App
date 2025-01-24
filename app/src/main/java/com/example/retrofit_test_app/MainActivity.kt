package com.example.retrofit_test_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofit_test_app.databinding.ActivityMainBinding
import com.example.retrofit_test_app.data.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { animList ->
            val adapter = AnimeListAdapter(this, animList)
            binding.recyclerView.adapter = adapter
            animList.forEach { anime ->
                Log.d("Anime Title", anime.title)
            }
        })
    }

}