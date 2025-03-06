package com.example.jetsnack.api

import com.example.jetsnack.model.PenumpangKereta
import com.example.jetsnack.model.PenumpangResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("od_20535_jml_penumpang_kereta_api_jarak_jauh_daop_2__stasiu_v1")
    suspend fun getPenumpang(): PenumpangResponse
}
