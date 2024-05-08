@file:UnstableApi package com.livmas.player

import android.content.ComponentName
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.livmas.player.data.datasource_factories.MyBaseDataSourceFactory
import com.livmas.player.domain.MusicPlayer
import com.livmas.player.domain.usecases.GetMediaItemByUrlUseCase
import com.livmas.player.presentation.fragments.player.PlayerViewModel
import com.livmas.player.presentation.services.MusicPlaybackService
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import com.livmas.util.domain.usecases.LikeTrackUseCase
import com.livmas.util.domain.usecases.UnlikeTrackUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    single {
        MyBaseDataSourceFactory(get())
    }

    single<MediaSource.Factory> {
        DefaultMediaSourceFactory(
            get<MyBaseDataSourceFactory>()
        )
    }

    single<MusicPlayer> {
        MusicPlayer(get(), get(), get())
    }

    single {
        SessionToken(get(), ComponentName(get(), MusicPlaybackService::class.java))
    }
    single<ListenableFuture<MediaController>> {
        MediaController.Builder(get(), get()).buildAsync()
    }

    single { GetMediaItemByUrlUseCase(get()) }
    single { GetTrackURLUseCase(get()) }
    single { LikeTrackUseCase(get()) }
    single { UnlikeTrackUseCase(get()) }
    viewModel { PlayerViewModel(get(), get(), get()) }
}