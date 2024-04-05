package com.livmas.player

import com.livmas.player.domain.usecases.GetMediaItemUseCase
import com.livmas.player.ui.fragments.player.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    single { GetMediaItemUseCase(get()) }
    viewModel{ PlayerViewModel(get()) }
}