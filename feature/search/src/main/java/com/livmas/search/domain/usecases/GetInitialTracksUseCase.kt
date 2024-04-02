package com.livmas.search.domain.usecases

import com.livmas.util.domain.repositories.TrackInfoRepository
import com.livmas.util.domain.models.TrackDTO

internal class GetInitialTracksUseCase(private val repository: TrackInfoRepository) {
    suspend fun execute(): List<TrackDTO> {
        return repository.getTracks()
    }
}
