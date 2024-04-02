package com.livmas.util.domain.repositories

import com.livmas.util.domain.models.TrackDTO

interface TrackInfoRepository {
    fun getTracks(): List<TrackDTO>
    fun searchTracks(query: String): List<TrackDTO>
    fun getTrackURLById(id: Long): String
    fun setIsTrackLiked(trackId: Long, likedState: Boolean)
}