package com.livmas.player.domain

import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource

@UnstableApi
internal class MusicPlayer(val exoPlayer: ExoPlayer) {
    fun playSourceTracks(playlist: List<MediaSource>) {
        exoPlayer.clearMediaItems()
        exoPlayer.setMediaSources(playlist)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    fun playItemTracks(playlist: List<MediaItem>) {
        exoPlayer.clearMediaItems()
        exoPlayer.setMediaItems(playlist)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }
}