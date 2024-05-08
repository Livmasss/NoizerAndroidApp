package com.livmas.data.models

import com.google.gson.annotations.SerializedName

internal data class TrackModel (
    val id: Long,
    val title: String,
    val author: String,
    @SerializedName("coverUri")
    val coverUrl: String
)