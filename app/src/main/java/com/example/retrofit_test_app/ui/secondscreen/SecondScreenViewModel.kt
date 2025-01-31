package com.example.retrofit_test_app.ui.secondscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondScreenViewModel: ViewModel() {

    private val _playbackTime = MutableLiveData(0f)
    val playbackTime: LiveData<Float> get() = _playbackTime

    fun savePlaybackTime(time: Float) {
        _playbackTime.value = time
    }
}