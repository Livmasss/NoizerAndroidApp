package com.livmas.util.domain.usecases

import com.livmas.util.domain.exceptions.TrackNotFoundException
import com.livmas.util.domain.repositories.TrackInfoRepository

class GetTrackURLUseCase(private val repository: TrackInfoRepository) {
    suspend fun execute(id: Long): String? {
        return try {
            repository.getTrackURLById(id)
        } catch (e: TrackNotFoundException) {
            println("Track with id not found: $id")
            null
        }
    }
}