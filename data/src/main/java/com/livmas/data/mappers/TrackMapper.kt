package com.livmas.data.mappers

import com.livmas.data.models.TrackModel
import com.livmas.util.domain.models.TrackDTO

internal class TrackMapper {
    companion object {
        fun mapTrackToDTO(track: TrackModel) = TrackDTO(
            track.id,
            track.title,
            track.author,
            track.coverUrl,
            track.likedState
        )
    }
}