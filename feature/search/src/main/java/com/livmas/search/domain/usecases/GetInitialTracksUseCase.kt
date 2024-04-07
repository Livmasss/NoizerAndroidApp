package com.livmas.search.domain.usecases

import com.livmas.search.domain.repositories.TrackRepository
import com.livmas.util.domain.models.TrackDTO

internal class GetInitialTracksUseCase(private val repository: TrackRepository) {
    suspend fun execute(): List<TrackDTO> {
        return repository.getTracks()
    }
}
