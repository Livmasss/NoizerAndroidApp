package com.livmas.data.repositories

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import com.livmas.player.domain.repositories.MediaRepository

@UnstableApi
internal class MediaItemRepositoryImpl(override val context: Context) : MediaRepository {
    override fun getMediaItemBuilderByURI(uri: String): MediaItem.Builder =
        MediaItem.Builder().setUri(uri)
}