package com.livmas.data.apis

import com.livmas.data.models.TracksInfoResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.URI

internal interface RemoteTrackInfoApi {

    @GET("/search")
    fun getTracksByTitle(@Query("title") title: String): Call<URI>

    @GET("/feed")
    fun getTracksFeed(): Call<TracksInfoResponseModel>
}
