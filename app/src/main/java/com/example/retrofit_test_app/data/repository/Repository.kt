package com.example.retrofit_test_app.data.repository

import android.util.Log
import com.example.retrofit_test_app.data.api.RetrofitInstance
import com.example.retrofit_test_app.data.model.AnimeResponse

class Repository {

    suspend fun getPost(): Result<AnimeResponse> {
        return try {
            val response = RetrofitInstance.api.getAnimeList()
            Result.success(response)
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching posts: ${e.message}")
            Result.failure(e)
        }
    }
}
