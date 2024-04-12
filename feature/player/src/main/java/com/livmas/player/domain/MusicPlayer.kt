package com.livmas.player.domain

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer

@UnstableApi
@Deprecated("Replaced with MusicPlaybackService")
internal class MusicPlayer(val exoPlayer: ExoPlayer, private val context: Context) {

    fun playItemTracks(playlist: List<MediaItem>) {
        exoPlayer.clearMediaItems()
        exoPlayer.setMediaItem(playlist[0])
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }
}