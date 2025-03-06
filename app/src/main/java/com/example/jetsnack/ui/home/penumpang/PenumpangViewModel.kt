package com.example.jetsnack.ui.home.penumpang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetsnack.api.ApiService
import com.example.jetsnack.api.Retrofit
import com.example.jetsnack.model.PenumpangKereta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<PenumpangKereta>>(emptyList())
    val users: StateFlow<List<PenumpangKereta>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = Retrofit.instance.getPenumpang()
                _users.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
