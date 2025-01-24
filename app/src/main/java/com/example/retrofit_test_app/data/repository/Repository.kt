package com.example.retrofit_test_app.data.repository

import com.example.retrofit_test_app.data.api.RetrofitInstance
import com.example.retrofit_test_app.data.model.AnimeResponse

class Repository {

    suspend fun getPost(): AnimeResponse{
        return RetrofitInstance.api.getAnimeList()
    }
}