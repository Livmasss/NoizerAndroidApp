package com.livmas.data.apis

import com.livmas.data.models.TracksInfoResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RemoteTrackInfoApi {

    @GET("/search")
    fun findTracksByQuery(@Query("query") query: String): Call<TracksInfoResponseModel>

    @GET("/feed")
    fun getTracksFeed(): Call<TracksInfoResponseModel>
}
