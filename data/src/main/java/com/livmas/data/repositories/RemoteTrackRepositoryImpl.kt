package com.livmas.data.repositories

import android.util.Log
import com.livmas.data.datasources.RemoteMediaDataSource
import com.livmas.data.datasources.RemoteTrackInfoDataSource
import com.livmas.data.mappers.TrackMapper
import com.livmas.util.domain.models.TrackDTO
import com.livmas.util.domain.repositories.TrackInfoRepository

internal class RemoteTrackRepositoryImpl(
    private val remoteTrackInfoDataSource: RemoteTrackInfoDataSource,
    private val remoteMediaDataSource: RemoteMediaDataSource
): TrackInfoRepository {
    override fun getTracks(): List<TrackDTO> =
        remoteTrackInfoDataSource.getTracks().map {
            TrackMapper.mapTrackToDTO(it)
        }

    override fun searchTracks(query: String): List<TrackDTO> =
        remoteTrackInfoDataSource.findTracks(query).map {
            TrackMapper.mapTrackToDTO(it)
        }

    override fun getTrackURLById(id: Long): String {
        return remoteMediaDataSource.getTrackURLById(id)
    }

    override fun setIsTrackLiked(trackId: Long, likedState: Boolean) {
        Log.d("like", "Track with id $trackId is " +
            if (likedState)
                "liked"
            else
                "unliked"
        )
    }
}