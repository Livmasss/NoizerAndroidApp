package com.livmas.search.ui.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class SearchViewModel : ViewModel() {

    val searchQuery = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
}