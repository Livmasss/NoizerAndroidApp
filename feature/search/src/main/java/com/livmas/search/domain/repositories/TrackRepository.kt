package com.livmas.search.domain.repositories

import com.livmas.search.domain.models.TrackDTO

interface TrackRepository {
    fun getTracks(): List<TrackDTO>
    fun searchTracks(query: String): List<TrackDTO>
}