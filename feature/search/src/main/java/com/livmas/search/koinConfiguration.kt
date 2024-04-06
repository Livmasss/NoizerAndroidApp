package com.livmas.search

import com.livmas.search.domain.usecases.GetInitialTracksUseCase
import com.livmas.search.domain.usecases.SearchTracksUseCase
import com.livmas.search.presentation.fragments.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single {
        SearchTracksUseCase(get())
    }
    single {
        GetInitialTracksUseCase(get())
    }
    viewModel<SearchViewModel> {
        SearchViewModel(get(), get())
    }
}