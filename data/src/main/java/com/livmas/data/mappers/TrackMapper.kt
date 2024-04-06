package com.livmas.data.mappers

import com.livmas.data.models.TrackModel
import com.livmas.search.domain.models.TrackDTO

class TrackMapper {
    companion object {
        fun mapTrackToDTO(track: TrackModel) = TrackDTO(
            track.title,
            track.author,
            track.coverUrl
        )
    }
}