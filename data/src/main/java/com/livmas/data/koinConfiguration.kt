@file:UnstableApi package com.livmas.data

import androidx.media3.common.util.UnstableApi
import com.livmas.data.datasources.RemoteMediaDataSource
import com.livmas.data.datasources.RemoteTrackInfoDataSource
import com.livmas.data.repositories.RemoteMediaItemRepositoryImpl
import com.livmas.data.repositories.RemoteTrackRepositoryImpl
import com.livmas.player.domain.repositories.MediaRepository
import com.livmas.util.domain.repositories.TrackInfoRepository
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
        RemoteMediaDataSource(get())
    }

    single<MediaRepository> {
        RemoteMediaItemRepositoryImpl(get())
    }
    single<TrackInfoRepository> {
        RemoteTrackRepositoryImpl(get(), get())
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
                .baseUrl("${BuildConfig.API_URL}:${BuildConfig.API_PORT}")
                .client(get())
                .build()
        }
        catch (e: Exception) {
            null
        }
    }
}