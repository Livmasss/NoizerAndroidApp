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

@UnstableApi
internal class PlayerViewModel(
    private val repository: GetMediaItemUseCase,
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
        return repository.execute(uri)
    }

    fun playTrack(track: TrackModel) {
        player.playItemTracks(listOf(getSongByUri(track.trackUrl)))
        _playedTrack.postValue(track)
    }
}