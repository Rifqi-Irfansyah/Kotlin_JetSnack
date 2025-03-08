package com.example.jetsnack.ui

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.jetsnack.data.ProfileEntity
import com.proyek.jtk.data.AppDatabase
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).dataDao()
    val dataList: LiveData<List<ProfileEntity>> = dao.getProfile()

    fun upsertProfile(nama: String, email: String, imageUri: String) {
        viewModelScope.launch {
            try {
                val currentData = dataList.value?.firstOrNull()
                Log.d("Profile", "Stored imageUri: ${imageUri}")
                if (currentData != null) {
                    val updatedProfile = currentData.copy(nama = nama, email = email, imageUri = imageUri)
                    dao.update(updatedProfile)
                } else {
                    with(dao) { insert(ProfileEntity(nama = nama, email = email, imageUri = imageUri)) }
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error upserting profile: ${e.message}", e)
            }
        }
    }
}