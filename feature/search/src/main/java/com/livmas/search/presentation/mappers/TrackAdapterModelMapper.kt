package com.livmas.search.presentation.mappers

import com.livmas.search.presentation.adapters.SearchAdapter
import com.livmas.ui.presentation.models.TrackModel
import com.livmas.util.domain.models.TrackDTO

internal class TrackAdapterModelMapper {
    companion object {
        fun fromDTO(dto: TrackDTO) = SearchAdapter.TrackAdapterModel(
            dto.id,
            dto.title,
            dto.author,
            dto.coverUrl,
            dto.likedState
        )

        fun toDTO(trackModel: SearchAdapter.TrackAdapterModel): TrackDTO = TrackDTO(
            trackModel.id,
            trackModel.title,
            trackModel.author,
            trackModel.coverUrl,
            trackModel.likedState
        )

        fun fromPresentationModel(model: TrackModel) = SearchAdapter.TrackAdapterModel(
            model.id,
            model.title,
            model.author,
            model.coverUrl,
            model.likedState
        )

        fun toPresentationModel(trackModel: SearchAdapter.TrackAdapterModel) = TrackModel(
            trackModel.id,
            trackModel.title,
            trackModel.author,
            trackModel.coverUrl,
            trackModel.likedState
        )
    }
}