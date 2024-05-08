package com.livmas.player.domain

import android.net.Uri
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.ui.PlayerControlView
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.livmas.player.domain.usecases.GetMediaItemByUrlUseCase
import com.livmas.ui.presentation.models.TrackModel
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@UnstableApi
internal class MusicPlayer(
    private val getMediaItemByUrlUseCase: GetMediaItemByUrlUseCase,
    private val getTrackURLUseCase: GetTrackURLUseCase,
    private val controllerFuture: ListenableFuture<MediaController>,
) {
    fun playItemTracks(playlist: List<TrackModel>) =
        controllerFuture.addListener(
            {
                val player = controllerFuture.get()
                player.repeatMode = Player.REPEAT_MODE_ALL

                CoroutineScope(Dispatchers.IO).launch {
                    playlist.forEach { track ->
                        val url = getTrackURLUseCase.execute(track.id) ?: return@forEach
                        val mediaItem = getMediaItemByUrlUseCase.execute(Uri.parse(url), track)

                        CoroutineScope(Dispatchers.Main).launch {
                            player.apply {
                                setMediaItem(mediaItem)
                                prepare()
                                playWhenReady = true
                            }
                        }
                    }
                }
            },
            MoreExecutors.directExecutor()
        )

    fun setupPlayerForView(view: PlayerControlView) =
        controllerFuture.addListener({
            view.player = controllerFuture.get()
        },
            MoreExecutors.directExecutor()
        )
}
