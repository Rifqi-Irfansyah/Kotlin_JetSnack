package com.example.jetsnack.model

import com.google.gson.annotations.SerializedName

data class PenumpangResponse(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: List<PenumpangKereta>
)
