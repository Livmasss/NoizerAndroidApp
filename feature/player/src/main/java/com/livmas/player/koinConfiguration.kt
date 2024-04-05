package com.livmas.player

import com.livmas.player.ui.fragments.player.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    viewModel{ PlayerViewModel(get()) }
}