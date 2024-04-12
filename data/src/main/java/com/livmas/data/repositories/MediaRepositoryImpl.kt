package com.livmas.data.repositories

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import com.livmas.player.domain.repositories.MediaRepository

@UnstableApi
internal class MediaRepositoryImpl(override val context: Context) : MediaRepository {
    override fun getMediaItemByURI(uri: String): MediaItem {
        val item = MediaItem.fromUri(uri)
        return item
    }
}