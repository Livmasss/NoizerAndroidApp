@file:UnstableApi package com.livmas.data

import androidx.media3.common.util.UnstableApi
import com.livmas.data.datasources.RemoteTrackContentDataSource
import com.livmas.data.datasources.RemoteTrackInfoDataSource
import com.livmas.data.repositories.MediaItemRepositoryImpl
import com.livmas.data.repositories.TrackRepositoryImpl
import com.livmas.player.domain.repositories.MediaRepository
import com.livmas.util.domain.repositories.TrackRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        RemoteTrackInfoDataSource()
    }
    single {
        RemoteTrackContentDataSource()
    }

    single<MediaRepository> {
        MediaItemRepositoryImpl(get())
    }
    single<TrackRepository> {
        TrackRepositoryImpl(get(), get())
    }
}