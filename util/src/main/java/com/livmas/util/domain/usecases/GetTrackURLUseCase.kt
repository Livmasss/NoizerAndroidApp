package com.livmas.util.domain.usecases

import com.livmas.util.domain.exceptions.TrackNotFoundException
import com.livmas.util.domain.repositories.TrackRepository

class GetTrackURLUseCase(private val repository: TrackRepository) {
    suspend fun execute(id: Long): String? {
        return try {
            repository.getTrackURLById(id)
        } catch (e: TrackNotFoundException) {
            println("Track with id not found: $id")
            null
        }
    }
}