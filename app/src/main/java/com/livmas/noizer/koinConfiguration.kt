package com.livmas.noizer

import android.app.Application
import com.livmas.data.dataModule
import com.livmas.player.playerModule
import com.livmas.search.searchModule
import com.livmas.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.setupKoin() {
    startKoin {
        androidLogger()
        androidContext(applicationContext)
        modules(dataModule, playerModule, searchModule, uiModule)
    }
}