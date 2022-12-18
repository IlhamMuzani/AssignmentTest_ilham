package com.ilham.myapplication.model

import com.google.gson.annotations.SerializedName

data class DataVideo (

    @SerializedName("id") val id: Long?,
    @SerializedName("judul") val judul: String?,
    @SerializedName("kategori") val kategori: String?,
    @SerializedName("harga") val harga: String?,
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("video") val video: String,
    )