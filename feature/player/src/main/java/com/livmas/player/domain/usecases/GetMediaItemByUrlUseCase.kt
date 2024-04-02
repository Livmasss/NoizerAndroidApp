package com.livmas.player.domain.usecases

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.UnstableApi
import com.livmas.player.domain.repositories.MediaRepository
import com.livmas.ui.presentation.models.TrackModel

@UnstableApi
internal class GetMediaItemByUrlUseCase(
    private val repository: MediaRepository
) {
    fun execute(trackUri: Uri, track: TrackModel): MediaItem =
        repository.getMediaItemBuilderByURI(trackUri).setMediaMetadata(
            MediaMetadata.Builder()
                .setArtist(track.author)
                .setTitle(track.title)
                .setArtworkUri(Uri.parse(track.coverUrl))
                .build()
        ).build()
}