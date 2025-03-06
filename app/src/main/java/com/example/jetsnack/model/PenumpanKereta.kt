package com.example.jetsnack.model

data class PenumpangKereta(
    val kode_provinsi: String,
    val nama_provinsi: String,
    val nama_stasiun: String,
    val penumpang_naik_kereta: Int,
    val penumpang_turun_kereta: Int,
    val satuan: String,
    val tahun: Int
)
