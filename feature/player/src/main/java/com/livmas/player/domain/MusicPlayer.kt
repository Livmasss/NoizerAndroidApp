package com.livmas.player.domain

import android.net.Uri
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.ui.PlayerControlView
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.livmas.player.domain.usecases.GetMediaItemBuilderUseCase
import com.livmas.ui.presentation.models.TrackModel
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@UnstableApi
internal class MusicPlayer(
    private val getMediaItemBuilderUseCase: GetMediaItemBuilderUseCase,
    private val getTrackURLUseCase: GetTrackURLUseCase,
    private val controllerFuture: ListenableFuture<MediaController>,
) {
    fun playItemTracks(playlist: List<TrackModel>) =
        controllerFuture.addListener(
            {
                CoroutineScope(Dispatchers.IO).launch {
                    playlist.forEach { track ->
                        val url = getTrackURLUseCase.execute(track.id)
                        val item = getMediaItemBuilderUseCase.execute(url)
                            .setMediaMetadata(
                                MediaMetadata.Builder()
                                    .setArtist(track.author)
                                    .setTitle(track.title)
                                    .setArtworkUri(Uri.parse(track.coverUrl))
                                    .build()
                            )
                            .build()

                        CoroutineScope(Dispatchers.Main).launch {
                            controllerFuture.get().apply {
                                setMediaItem(item)
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
