package com.livmas.data.datasources

import com.livmas.data.apis.RemoteTrackContentApi
import com.livmas.util.domain.exceptions.TrackNotFoundException
import retrofit2.Retrofit

internal class RemoteTrackContentDataSource (
    private val retrofit: Retrofit?
) {
    private val api: RemoteTrackContentApi? = createApi()

    private fun createApi(): RemoteTrackContentApi? =
        retrofit?.create(RemoteTrackContentApi::class.java)

    fun getTrackURLById(id: Long): String {
        return api?.getTrackContentById(id)?.execute()?.body()
            ?: throw TrackNotFoundException()
    }
}
