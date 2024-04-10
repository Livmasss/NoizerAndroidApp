package com.livmas.util.domain.models

data class TrackDTO (
    val id: Long,
    val title: String,
    val author: String,
    val coverUrl: String,
    val likedState: Boolean
)