package com.ilham.myapplication.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailVideo(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("videos") val dataVideo: DataVideo?
)