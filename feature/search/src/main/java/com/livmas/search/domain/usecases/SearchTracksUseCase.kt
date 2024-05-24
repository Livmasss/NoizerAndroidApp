package com.livmas.search.domain.usecases

import com.livmas.util.domain.repositories.TrackInfoRepository
import com.livmas.util.domain.models.TrackDTO

internal class SearchTracksUseCase(private val repository: TrackInfoRepository) {
    suspend fun execute(query: String): List<TrackDTO> {
        return repository.searchTracks(query)
    }
}
