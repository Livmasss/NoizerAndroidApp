package com.livmas.ui.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.util.domain.models.TrackDTO

class SharedViewModel: ViewModel() {
    val isPlayerVisible: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }
    val trackToPlay: MutableLiveData<TrackDTO> by lazy {
        MutableLiveData()
    }
}