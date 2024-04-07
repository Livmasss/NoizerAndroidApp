package com.livmas.search.domain.usecases

import com.livmas.search.domain.repositories.TrackRepository
import com.livmas.util.domain.models.TrackDTO

internal class SearchTracksUseCase(private val repository: TrackRepository) {
    suspend fun execute(query: String): List<TrackDTO> {
        return repository.searchTracks(query)
    }
}
