@file:UnstableApi package com.livmas.data

import androidx.media3.common.util.UnstableApi
import com.livmas.data.datasources.RemoteTrackContentDataSource
import com.livmas.data.datasources.RemoteTrackInfoDataSource
import com.livmas.data.repositories.MediaItemRepositoryImpl
import com.livmas.data.repositories.TrackRepositoryImpl
import com.livmas.player.domain.repositories.MediaRepository
import com.livmas.util.domain.repositories.TrackRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        RemoteTrackInfoDataSource(get())
    }
    single {
        RemoteTrackContentDataSource(get())
    }

    single<MediaRepository> {
        MediaItemRepositoryImpl(get())
    }
    single<TrackRepository> {
        TrackRepositoryImpl(get(), get())
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single<Retrofit?> {
        try {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.6:8080")
                .client(get())
                .build()
        }
        catch (e: Exception) {
            null
        }
    }
}