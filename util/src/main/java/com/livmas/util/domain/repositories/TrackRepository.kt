package com.livmas.util.domain.repositories

import com.livmas.util.domain.models.TrackDTO

interface TrackRepository {
    fun getTracks(): List<TrackDTO>
    fun searchTracks(query: String): List<TrackDTO>
    fun getTrackURLById(id: Long): String
}