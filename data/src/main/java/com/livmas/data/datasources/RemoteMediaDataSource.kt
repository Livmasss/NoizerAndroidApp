package com.livmas.data.datasources

import com.livmas.data.apis.MediaApi
import com.livmas.util.domain.exceptions.TrackNotFoundException
import retrofit2.Retrofit

internal class RemoteMediaDataSource (
    private val retrofit: Retrofit?
) {
    private val api: MediaApi? = createApi()

    private fun createApi(): MediaApi? =
        retrofit?.create(MediaApi::class.java)

    fun getTrackURLById(id: Long): String {
        return api?.getTrackContentById(id)?.execute()?.body()
            ?: throw TrackNotFoundException()
    }
}
