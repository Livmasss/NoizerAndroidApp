package com.livmas.player.domain

import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer

@UnstableApi
internal class MusicPlayer(val exoPlayer: ExoPlayer) {

    fun playItemTracks(playlist: List<MediaItem>) {
        exoPlayer.clearMediaItems()
        exoPlayer.setMediaItems(playlist)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }
}