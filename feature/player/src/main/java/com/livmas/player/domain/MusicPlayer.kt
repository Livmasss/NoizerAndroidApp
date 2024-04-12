package com.livmas.player.domain

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource

@UnstableApi
internal class MusicPlayer(val exoPlayer: ExoPlayer, private val context: Context) {

    fun playTest() {
        val item = MediaItem.fromUri("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")

        val httpDataSourceFactory = DefaultHttpDataSource.Factory().apply {
            setAllowCrossProtocolRedirects(true)
        }

        val dataSourceFactory = DefaultDataSource.Factory(
            context,
            httpDataSourceFactory
        )
        val mediaSource = HlsMediaSource.Factory(dataSourceFactory).createMediaSource(item)

        exoPlayer.setMediaItem(mediaSource.mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }
    fun playItemTracks(playlist: List<MediaItem>) {
        exoPlayer.clearMediaItems()
        exoPlayer.setMediaItem(playlist[0])
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }
}