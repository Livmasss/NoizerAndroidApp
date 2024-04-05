package com.livmas.data.repositories

import androidx.media3.common.MediaItem
import com.livmas.player.domain.repositories.MediaRepository

class MediaRepositoryImpl: MediaRepository {
    override fun getMediaItemByURI(uri: String): MediaItem {
        return MediaItem.fromUri(uri)
    }
}