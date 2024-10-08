package com.livmas.search.presentation.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.search.domain.usecases.GetInitialTracksUseCase
import com.livmas.search.domain.usecases.SearchTracksUseCase
import com.livmas.ui.presentation.mappers.TrackModelMapper
import com.livmas.ui.presentation.models.TrackModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val searchTracksUseCase: SearchTracksUseCase,
    private val getInitialTracksUseCase: GetInitialTracksUseCase,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {

    val searchResult: LiveData<List<TrackModel>>
        get() = _searchResult
    private val _searchResult: MutableLiveData<List<TrackModel>> by lazy {
        MutableLiveData(listOf())
    }

    fun findTracks(searchQuery: String) {
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            val data = searchTracksUseCase.execute(searchQuery).map {
                TrackModelMapper.fromDTO(it)
            }
            _searchResult.postValue(data)
        }
    }

    fun initiateTracks() {
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            val data = getInitialTracksUseCase.execute().map {
                TrackModelMapper.fromDTO(it)
            }
            _searchResult.postValue(data)
        }
    }
}