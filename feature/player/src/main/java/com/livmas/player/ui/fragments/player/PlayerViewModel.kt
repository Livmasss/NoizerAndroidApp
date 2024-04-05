package com.livmas.player.ui.fragments.player

import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import com.livmas.player.domain.usecases.GetMediaItemUseCase

internal class PlayerViewModel(private val repository: GetMediaItemUseCase): ViewModel() {
    fun getSongByUri(uri: String): MediaItem {
        return repository.execute(uri)
    }
}