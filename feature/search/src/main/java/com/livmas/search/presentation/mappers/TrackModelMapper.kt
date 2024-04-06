package com.livmas.search.presentation.mappers

import com.livmas.search.domain.models.TrackDTO
import com.livmas.search.presentation.models.TrackModel

class TrackModelMapper {
    companion object {
        fun fromDTO(dto: TrackDTO) = TrackModel(
            dto.title,
            dto.author,
            dto.coverUrl
        )
    }
}