package com.ilham.myapplication.model

import com.google.gson.annotations.SerializedName

data class ResponselistVideo(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("videos") val datavideo: List<DataVideo>
)