package com.livmas.search.domain.usecases

import com.livmas.search.domain.models.TrackDTO
import com.livmas.search.domain.repositories.TrackRepository

class SearchTracksUseCase(private val repository: TrackRepository) {
    suspend fun execute(query: String): List<TrackDTO> {
        return repository.searchTracks(query)
    }
}
