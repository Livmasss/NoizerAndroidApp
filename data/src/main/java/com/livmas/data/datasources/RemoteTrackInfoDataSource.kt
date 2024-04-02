package com.livmas.data.datasources

import com.livmas.data.apis.TrackInfoApi
import com.livmas.data.models.TrackModel
import retrofit2.Retrofit
import java.io.IOException

internal class RemoteTrackInfoDataSource (
    private val retrofit: Retrofit?
) {
    private val api = createApi()

    fun getTracks(): List<TrackModel> {
        if (api == null)
            throw IOException()

        return try {
            val response = api.getTracksFeed().execute()
            if (response.isSuccessful)
                response.body()?.items ?: emptyList()
            else
                emptyList()
        }
        finally {
            emptyList<TrackModel>()
        }
    }

    fun findTracks(query: String): List<TrackModel> {
        if (api == null)
            throw IOException()

        val response = api.findTracksByQuery(query).execute()
        return if (response.isSuccessful)
            response.body()?.items ?: emptyList()
        else
            emptyList()
    }

    private fun createApi(): TrackInfoApi? =
        retrofit?.create(TrackInfoApi::class.java)
}
