package com.livmas.noizer

import android.app.Application
import com.livmas.data.dataModule
import com.livmas.player.playerModule
import com.livmas.search.searchModule
import com.livmas.ui.uiModule
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.setupKoin() {
    startKoin {
        androidLogger()
        androidContext(applicationContext)
        modules(mainModule, dataModule, playerModule, searchModule, uiModule)
    }
}

val mainModule = module {
    factory<CoroutineExceptionHandler> {
        CoroutineExceptionHandler { _, exception ->
            println("Exception occurred: $exception")
        }
    }
}