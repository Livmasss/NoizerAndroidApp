package com.livmas.player.ui.models

data class TrackModel (
    val id: Long,
    val title: String,
    val author: String,
    val coverUrl: String,
    var likedState: Boolean
)