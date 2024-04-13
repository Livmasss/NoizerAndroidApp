package com.livmas.player.presentation.fragments.player

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.ui.PlayerControlView
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.livmas.player.domain.usecases.GetMediaItemBuilderUseCase
import com.livmas.player.presentation.models.TrackModel
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import com.livmas.util.domain.usecases.LikeTrackUseCase
import com.livmas.util.domain.usecases.UnlikeTrackUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@UnstableApi
internal class PlayerViewModel(
    private val getMediaItemBuilderUseCase: GetMediaItemBuilderUseCase,
    private val getTrackURLUseCase: GetTrackURLUseCase,
    private val likeTrackUseCase: LikeTrackUseCase,
    private val unlikeTrackUseCase: UnlikeTrackUseCase,
    private val controllerFuture: ListenableFuture<MediaController>
): ViewModel() {

    private val _playedTrack: MutableLiveData<TrackModel> by lazy {
        MutableLiveData()
    }
    val playedTrack: LiveData<TrackModel>
        get() = _playedTrack

    fun setupPlayerForView(view: PlayerControlView) =
        controllerFuture.addListener({
            view.player = controllerFuture.get()
        },
            MoreExecutors.directExecutor()
        )

    fun changePlayedTrackLikedState() =
        playedTrack.value?.let { track ->
            track.likedState = !track.likedState

            if (track.likedState)
                likeTrackUseCase.execute(track.id)
            else
                unlikeTrackUseCase.execute(track.id)
        }

    fun playTrack(track: TrackModel) =
        controllerFuture.addListener(
            {
                CoroutineScope(Dispatchers.IO).launch {
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
                    _playedTrack.postValue(track)

                    CoroutineScope(Dispatchers.Main).launch {
                        controllerFuture.get().apply {
                            setMediaItem(item)
                            prepare()
                            playWhenReady = true
                        }
                    }
                }
            },
            MoreExecutors.directExecutor()
        )
}