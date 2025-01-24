package com.example.retrofit_test_app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_test_app.data.model.Data
import com.example.retrofit_test_app.data.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<List<Data>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            val animeList = response.data

            myResponse.value = animeList

            animeList.forEach { anime ->
//                myResponse.value = listOf(anime)
                Log.d("title", "${anime.title}")

            }


        }

    }
}