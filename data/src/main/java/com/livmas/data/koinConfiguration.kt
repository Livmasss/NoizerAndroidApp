package com.livmas.data

import com.livmas.data.datasources.TrackDataSource
import com.livmas.data.repositories.MediaRepositoryImpl
import com.livmas.data.repositories.TrackRepositoryImpl
import com.livmas.player.domain.repositories.MediaRepository
import com.livmas.search.domain.repositories.TrackRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        TrackDataSource()
    }

    single<MediaRepository> {
        MediaRepositoryImpl()
    }
    single<TrackRepository> {
        TrackRepositoryImpl(get())
    }
}