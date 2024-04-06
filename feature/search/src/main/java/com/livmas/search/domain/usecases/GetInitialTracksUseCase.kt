package com.livmas.search.domain.usecases

import com.livmas.search.domain.models.TrackDTO
import com.livmas.search.domain.repositories.TrackRepository

class GetInitialTracksUseCase(private val repository: TrackRepository) {
    suspend fun execute(): List<TrackDTO> {
        return repository.getTracks()
    }
}
