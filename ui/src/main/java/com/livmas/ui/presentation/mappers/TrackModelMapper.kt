package com.livmas.ui.presentation.mappers

import com.livmas.ui.presentation.models.TrackModel
import com.livmas.util.domain.models.TrackDTO

class TrackModelMapper {
    companion object {
        fun fromDTO(dto: TrackDTO) = TrackModel(
            dto.id,
            dto.title,
            dto.author,
            dto.coverUrl,
            dto.likedState
        )

        fun toDTO(trackModel: TrackModel): TrackDTO = TrackDTO(
            trackModel.id,
            trackModel.title,
            trackModel.author,
            trackModel.coverUrl,
            trackModel.likedState
        )
    }
}