package com.livmas.data.datasources

import com.livmas.data.apis.RemoteTrackInfoApi
import com.livmas.data.models.TrackModel
import retrofit2.Retrofit

internal class RemoteTrackInfoDataSource (
    private val retrofit: Retrofit
) {
    private val api = createApi()

    fun getTracks(): List<TrackModel> {
        val response = api.getTracksFeed().execute()
        return if (response.isSuccessful)
             response.body()?.items ?: emptyList()
        else
            emptyList()
    }

    fun findTracks(query: String): List<TrackModel> {
        val response = api.findTracksByQuery(query).execute()
        return if (response.isSuccessful)
            response.body()?.items ?: emptyList()
        else
            emptyList()
    }

    private fun createApi(): RemoteTrackInfoApi =
        retrofit.create(RemoteTrackInfoApi::class.java)
}
