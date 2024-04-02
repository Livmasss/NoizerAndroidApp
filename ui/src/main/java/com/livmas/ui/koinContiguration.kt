package com.livmas.ui

import com.livmas.ui.presentation.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel{ SharedViewModel() }
}