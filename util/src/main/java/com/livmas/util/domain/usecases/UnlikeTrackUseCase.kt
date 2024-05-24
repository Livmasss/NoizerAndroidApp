package com.livmas.util.domain.usecases

import com.livmas.util.domain.repositories.TrackInfoRepository

class UnlikeTrackUseCase(private val repository: TrackInfoRepository) {
    fun execute(trackId: Long) =
        repository.setIsTrackLiked(trackId, false)
}