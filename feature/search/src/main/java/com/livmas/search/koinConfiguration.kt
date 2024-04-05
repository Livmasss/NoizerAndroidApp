package com.livmas.search

import com.livmas.search.ui.fragments.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel<SearchViewModel> {
        SearchViewModel()
    }
//    single<SongsRepository>{  }
}