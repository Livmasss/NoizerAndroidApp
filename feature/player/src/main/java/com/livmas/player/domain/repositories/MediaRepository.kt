package com.livmas.player.domain.repositories

import androidx.media3.common.MediaItem

interface MediaRepository {
    fun getMediaItemByURI(uri: String): MediaItem
}