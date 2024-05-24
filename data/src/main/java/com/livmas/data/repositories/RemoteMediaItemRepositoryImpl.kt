package com.livmas.data.repositories

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import com.livmas.player.domain.repositories.MediaRepository


@UnstableApi
internal class RemoteMediaItemRepositoryImpl(
    override val context: Context
) : MediaRepository {
    override fun getMediaItemBuilderByURI(uri: Uri): MediaItem.Builder =
        MediaItem.Builder().setUri(uri)
}