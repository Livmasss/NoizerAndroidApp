package com.livmas.data.repositories

import com.livmas.data.datasources.TrackContentDataSource
import com.livmas.data.datasources.TrackInfoDataSource
import com.livmas.data.mappers.TrackMapper
import com.livmas.util.domain.repositories.TrackRepository
import com.livmas.util.domain.models.TrackDTO

internal class TrackRepositoryImpl(
    private val trackInfoSource: TrackInfoDataSource,
    private val trackContentSource: TrackContentDataSource
): TrackRepository {
    override fun getTracks(): List<TrackDTO> =
        trackInfoSource.getTracks().map {
            TrackMapper.mapTrackToDTO(it)
        }

    override fun searchTracks(query: String): List<TrackDTO> {
        TODO("Not yet implemented")
    }

    override fun getTrackURLById(id: Long): String {
        return trackContentSource.getTrackURLById(id)
    }
}