package com.livmas.player.domain.repositories

import android.content.Context
import androidx.media3.common.MediaItem

interface MediaRepository {
    val context: Context
    fun getMediaItemByURI(uri: String): MediaItem
}