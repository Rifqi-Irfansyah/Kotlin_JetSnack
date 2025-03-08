package com.example.jetsnack.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {
    @Insert
    suspend fun insert(data: ProfileEntity)

    @Update
    suspend fun update(data: ProfileEntity)

    @Query("SELECT * FROM profile_table LIMIT 1")
    fun getProfile(): LiveData<List<ProfileEntity>>
}