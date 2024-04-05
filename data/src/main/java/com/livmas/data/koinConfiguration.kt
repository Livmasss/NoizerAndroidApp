package com.livmas.data

import com.livmas.data.repositories.MediaRepositoryImpl
import com.livmas.player.domain.repositories.MediaRepository
import org.koin.dsl.module

val dataModule = module {
    single<MediaRepository> {
        MediaRepositoryImpl()
    }
}