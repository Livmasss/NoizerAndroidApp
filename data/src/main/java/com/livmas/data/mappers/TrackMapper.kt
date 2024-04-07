package com.livmas.data.mappers

import com.livmas.data.models.TrackModel

internal class TrackMapper {
    companion object {
        fun mapTrackToDTO(track: TrackModel) = com.livmas.util.domain.models.TrackDTO(
            track.id,
            track.title,
            track.author,
            track.coverUrl
        )
    }
}