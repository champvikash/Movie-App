package com.example.retrofit_test_app.data.api

import com.example.retrofit_test_app.data.model.AnimeResponse
import retrofit2.http.GET

interface GetAnimeListApi {

    @GET("top/anime")
    suspend fun getAnimeList() : AnimeResponse


}