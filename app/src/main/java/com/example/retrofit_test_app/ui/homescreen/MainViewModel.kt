package com.example.retrofit_test_app.ui.homescreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_test_app.data.model.Data
import com.example.retrofit_test_app.data.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _myResponse = MutableLiveData<List<Data>>()
    val myResponse: LiveData<List<Data>> get() = _myResponse

    fun getPost() {
        viewModelScope.launch {
            repository.getPost().onSuccess { response ->
                _myResponse.value = response.data
            }.onFailure { exception ->

                Log.e("MainViewModel", "Error fetching posts: ${exception.message}")
            }
        }
    }
}
