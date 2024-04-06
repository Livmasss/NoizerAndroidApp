package com.livmas.search.presentation.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.search.domain.usecases.GetInitialTracksUseCase
import com.livmas.search.domain.usecases.SearchTracksUseCase
import com.livmas.search.presentation.adapters.SearchAdapter
import com.livmas.search.presentation.mappers.TrackModelMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val searchTracksUseCase: SearchTracksUseCase,
    private val getInitialTracksUseCase: GetInitialTracksUseCase
) : ViewModel() {

    val searchQuery: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val searchResult: LiveData<List<SearchAdapter.TrackModel>>
        get() = _searchResult
    private val _searchResult: MutableLiveData<List<SearchAdapter.TrackModel>> by lazy {
        MutableLiveData(listOf())
    }

    fun searchTracks() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = searchTracksUseCase.execute(searchQuery.value.orEmpty()).map {
                TrackModelMapper.fromDTO(it)
            }
            _searchResult.postValue(data)
        }
    }

    fun initiateTracks() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = getInitialTracksUseCase.execute().map {
                TrackModelMapper.fromDTO(it)
            }
            _searchResult.postValue(data)
        }
    }
}