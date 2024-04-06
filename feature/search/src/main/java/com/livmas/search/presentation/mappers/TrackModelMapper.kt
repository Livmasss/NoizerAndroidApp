package com.livmas.search.presentation.mappers

import com.livmas.search.domain.models.TrackDTO
import com.livmas.search.presentation.adapters.SearchAdapter

internal class TrackModelMapper {
    companion object {
        fun fromDTO(dto: TrackDTO) = SearchAdapter.TrackModel(
            dto.id,
            dto.title,
            dto.author,
            dto.coverUrl
        )
    }
}