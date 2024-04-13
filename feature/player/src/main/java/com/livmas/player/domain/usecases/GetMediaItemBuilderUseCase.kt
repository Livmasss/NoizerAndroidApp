package com.livmas.player.domain.usecases

import androidx.media3.common.MediaItem
import com.livmas.player.domain.repositories.MediaRepository

internal class GetMediaItemBuilderUseCase(private val repository: MediaRepository) {
    fun execute(uri: String): MediaItem.Builder =
        repository.getMediaItemBuilderByURI(uri)
}