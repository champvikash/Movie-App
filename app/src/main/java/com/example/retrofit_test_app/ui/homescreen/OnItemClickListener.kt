package com.example.retrofit_test_app.ui.homescreen

interface OnItemClickListener {
    fun onItemViewClick(
        clipUrl: String,
        poster: String,
        title: String,
        plot: String,
        genes: String,
        episodes: String,
        rating: String
    )
}