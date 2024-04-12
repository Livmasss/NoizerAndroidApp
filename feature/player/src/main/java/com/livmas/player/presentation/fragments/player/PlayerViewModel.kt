package com.livmas.player.presentation.fragments.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerControlView
import com.livmas.player.domain.MusicPlayer
import com.livmas.player.domain.usecases.GetMediaItemUseCase
import com.livmas.player.presentation.models.TrackModel
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import com.livmas.util.domain.usecases.LikeTrackUseCase
import com.livmas.util.domain.usecases.UnlikeTrackUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@UnstableApi
internal class PlayerViewModel(
    private val getMediaItemUseCase: GetMediaItemUseCase,
    private val getTrackURLUseCase: GetTrackURLUseCase,
    private val likeTrackUseCase: LikeTrackUseCase,
    private val unlikeTrackUseCase: UnlikeTrackUseCase,
    private val player: MusicPlayer
): ViewModel() {

    private val _playedTrack: MutableLiveData<TrackModel> by lazy {
        MutableLiveData()
    }
    val playedTrack: LiveData<TrackModel>
        get() = _playedTrack

    fun setupPlayerForView(view: PlayerControlView) {
        view.player = player.exoPlayer
    }

    private fun getSongByUri(uri: String): MediaItem {
        return getMediaItemUseCase.execute(uri)
    }

    fun changePlayedTrackLikedState() {
        playedTrack.value?.let { track ->
            track.likedState = !track.likedState

            if (track.likedState)
                likeTrackUseCase.execute(track.id)
            else
                unlikeTrackUseCase.execute(track.id)
        }
    }

    fun playTrack(track: TrackModel) {
        CoroutineScope(Dispatchers.IO).launch {
            _playedTrack.postValue(track)
            val trackUrl = getTrackURLUseCase.execute(track.id)

            CoroutineScope(Dispatchers.Main).launch {
                player.playItemTracks(listOf(getSongByUri(trackUrl)))
            }
        }
    }
}