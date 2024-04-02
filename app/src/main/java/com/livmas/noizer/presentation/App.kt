package com.livmas.noizer.presentation

import android.app.Application
import com.livmas.noizer.setupKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }
}