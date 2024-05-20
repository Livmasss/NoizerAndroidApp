package com.livmas.data.repositories

import android.util.Log
import com.livmas.data.datasources.RemoteTrackContentDataSource
import com.livmas.data.datasources.RemoteTrackInfoDataSource
import com.livmas.data.mappers.TrackMapper
import com.livmas.util.domain.models.TrackDTO
import com.livmas.util.domain.repositories.TrackRepository

internal class TrackRepositoryImpl(
    private val remoteTrackInfoSource: RemoteTrackInfoDataSource,
    private val remoteTrackContentSource: RemoteTrackContentDataSource
): TrackRepository {
    override fun getTracks(): List<TrackDTO> =
        remoteTrackInfoSource.getTracks().map {
            TrackMapper.mapTrackToDTO(it)
        }

    override fun searchTracks(query: String): List<TrackDTO> =
        remoteTrackInfoSource.findTracks(query).map {
            TrackMapper.mapTrackToDTO(it)
        }

    override fun getTrackURLById(id: Long): String {
        return remoteTrackContentSource.getTrackURLById(id)
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