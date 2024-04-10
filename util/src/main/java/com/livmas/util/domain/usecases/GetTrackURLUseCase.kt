package com.livmas.util.domain.usecases

import com.livmas.util.domain.repositories.TrackRepository

class GetTrackURLUseCase(private val repository: TrackRepository) {
    suspend fun execute(id: Long): String =
        repository.getTrackURLById(id)
}