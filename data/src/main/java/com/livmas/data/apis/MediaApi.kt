package com.livmas.data.apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MediaApi {
    @GET("/track")
    fun getTrackContentById(@Query("id") id: Long): Call<String>
}