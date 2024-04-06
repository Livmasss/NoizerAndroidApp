package com.livmas.data.repositories

import com.livmas.data.datasources.TrackDataSource
import com.livmas.data.mappers.TrackMapper
import com.livmas.search.domain.models.TrackDTO
import com.livmas.search.domain.repositories.TrackRepository

internal class TrackRepositoryImpl(private val source: TrackDataSource): TrackRepository {
    override fun getTracks(): List<TrackDTO> =
        source.getTracks().map {
            TrackMapper.mapTrackToDTO(it)
        }

    override fun searchTracks(query: String): List<TrackDTO> {
        TODO("Not yet implemented")
    }
}