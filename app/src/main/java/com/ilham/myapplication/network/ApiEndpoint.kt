package com.ilham.myapplication.network

import com.ilham.myapplication.model.ResponseDetailVideo
import com.ilham.myapplication.model.ResponselistVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("video-list")
    fun pemutarvideo(
    ): Call<ResponselistVideo>

    @GET("video-detail/{id}")
    fun detailvideo(
        @Path("id") id: Long
    ): Call<ResponseDetailVideo>

    @GET("video-search")
    fun Searchvideo(
        @Query("keyword") keyword: String,
    ): Call<ResponselistVideo>
}