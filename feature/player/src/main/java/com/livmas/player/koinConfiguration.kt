@file:UnstableApi package com.livmas.player

import android.content.ComponentName
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.livmas.player.domain.usecases.GetMediaItemBuilderUseCase
import com.livmas.player.presentation.fragments.player.PlayerViewModel
import com.livmas.player.presentation.services.MusicPlaybackService
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import com.livmas.util.domain.usecases.LikeTrackUseCase
import com.livmas.util.domain.usecases.UnlikeTrackUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    single<ExoPlayer> {
        ExoPlayer.Builder(get()).build()
    }

    single {
        SessionToken(get(), ComponentName(get(), MusicPlaybackService::class.java))
    }
    single<ListenableFuture<MediaController>> {
        MediaController.Builder(get(), get()).buildAsync()
    }

    single { GetMediaItemBuilderUseCase(get()) }
    single { GetTrackURLUseCase(get()) }
    single { LikeTrackUseCase(get()) }
    single { UnlikeTrackUseCase(get()) }
    viewModel{ PlayerViewModel(get(), get(), get(), get(), get()) }
}