@file:UnstableApi package com.livmas.player

import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.livmas.player.domain.MusicPlayer
import com.livmas.player.domain.usecases.GetMediaItemUseCase
import com.livmas.player.ui.fragments.player.PlayerViewModel
import com.livmas.util.domain.usecases.GetTrackURLUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    single<ExoPlayer> {
        ExoPlayer.Builder(get()).build()
    }
    single<MusicPlayer> {
        MusicPlayer(get())
    }
    single { GetMediaItemUseCase(get()) }
    single { GetTrackURLUseCase(get()) }
    viewModel{ PlayerViewModel(get(), get(), get()) }
}