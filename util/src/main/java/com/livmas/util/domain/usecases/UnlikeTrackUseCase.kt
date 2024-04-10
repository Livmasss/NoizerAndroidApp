package com.livmas.util.domain.usecases

import com.livmas.util.domain.repositories.TrackRepository

class UnlikeTrackUseCase(private val repository: TrackRepository) {
    fun execute(trackId: Long) =
        repository.setIsTrackLiked(trackId, false)
}