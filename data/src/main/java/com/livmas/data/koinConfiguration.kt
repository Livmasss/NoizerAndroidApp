package com.livmas.data

import com.livmas.data.datasources.TrackContentDataSource
import com.livmas.data.datasources.TrackInfoDataSource
import com.livmas.data.repositories.MediaRepositoryImpl
import com.livmas.data.repositories.TrackRepositoryImpl
import com.livmas.player.domain.repositories.MediaRepository
import com.livmas.util.domain.repositories.TrackRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        TrackInfoDataSource()
    }
    single {
        TrackContentDataSource()
    }

    single<MediaRepository> {
        MediaRepositoryImpl()
    }
    single<TrackRepository> {
        TrackRepositoryImpl(get(), get())
    }
}