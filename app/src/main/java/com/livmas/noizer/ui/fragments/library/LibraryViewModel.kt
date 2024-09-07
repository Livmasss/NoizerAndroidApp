package com.livmas.noizer.ui.fragments.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class LibraryViewModel : ViewModel() {
    val text: LiveData<String> by lazy {
        MutableLiveData("Library")
    }
}