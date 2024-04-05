package com.livmas.player.domain.usecases

import androidx.media3.common.MediaItem
import com.livmas.player.domain.repositories.MediaRepository

internal class GetMediaItemUseCase(private val repository: MediaRepository) {
    fun execute(uri: String): MediaItem {
        return repository.getMediaItemByURI(uri)
    }
}