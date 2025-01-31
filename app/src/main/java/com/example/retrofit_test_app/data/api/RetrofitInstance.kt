package com.example.retrofit_test_app.data.api

import com.example.retrofit_test_app.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: GetAnimeListApi by lazy {
        retrofit.create(GetAnimeListApi::class.java)
    }
}