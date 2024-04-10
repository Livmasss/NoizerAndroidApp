package com.livmas.player.ui.fragments.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerControlView
import com.livmas.player.domain.MusicPlayer
import com.livmas.player.domain.usecases.GetMediaItemUseCase
import com.livmas.player.ui.models.TrackModel
import com.livmas.util.domain.models.TrackDTO
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@UnstableApi
internal class PlayerViewModel(
    private val getMediaItemUseCase: GetMediaItemUseCase,
    private val getTrackURLUseCase: GetTrackURLUseCase,
    private val player: MusicPlayer
): ViewModel() {

    private val _playedTrack: MutableLiveData<TrackDTO> by lazy {
        MutableLiveData()
    }
    val playedTrack: LiveData<TrackDTO>
        get() = _playedTrack

    fun setupPlayerForView(view: PlayerControlView) {
        view.player = player.exoPlayer
    }
    private fun getSongByUri(uri: String): MediaItem {
        return getMediaItemUseCase.execute(uri)
    }

    fun playTrack(track: TrackDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            _playedTrack.postValue(track)
            val trackUrl = getTrackURLUseCase.execute(track.id)

            CoroutineScope(Dispatchers.Main).launch {
                player.playItemTracks(listOf(getSongByUri(trackUrl)))
            }
        }
    }
}