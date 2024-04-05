package com.livmas.player.ui.fragments.player

import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import com.livmas.player.domain.repositories.MediaRepository

internal class PlayerViewModel(private val repository: MediaRepository): ViewModel() {
    fun getSongByUri(uri: String): MediaItem {
        return repository.getMediaItemByURI(uri)
    }
}