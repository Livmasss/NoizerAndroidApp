package com.livmas.noizer.ui.fragments.home

import android.app.Application
import com.livmas.data.dataModule
import com.livmas.player.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(dataModule, playerModule)
        }
    }
}