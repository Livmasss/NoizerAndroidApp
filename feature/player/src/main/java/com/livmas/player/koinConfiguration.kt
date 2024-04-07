@file:UnstableApi package com.livmas.player

import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.livmas.player.domain.MusicPlayer
import com.livmas.player.domain.usecases.GetMediaItemUseCase
import com.livmas.player.ui.fragments.player.PlayerViewModel
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
    viewModel{ PlayerViewModel(get(), get()) }
}