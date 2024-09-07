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
    override fun getTracks(): List<TrackDTO> {
//        remoteTrackInfoDataSource.getTracks().map {
//            TrackMapper.mapTrackToDTO(it)
//        }

//        Mock implementation
        return listOf(
            TrackDTO(
            1,
            "Царица",
            "Anna Asti",
            "https://t2.genius.com/unsafe/504x504/https%3A%2F%2Fimages.genius.com%2F151d8f514655fbc49960bc43c377bc6e.1000x1000x1.png",
                likedState = true
            )
        )
    }

    override fun searchTracks(query: String): List<TrackDTO> =
        remoteTrackInfoDataSource.findTracks(query).map {
            TrackMapper.mapTrackToDTO(it)
        }

    override fun getTrackURLById(id: Long): String {
//        return remoteMediaDataSource.getTrackURLById(id)

//        Mock Implementation
        return "http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8"
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