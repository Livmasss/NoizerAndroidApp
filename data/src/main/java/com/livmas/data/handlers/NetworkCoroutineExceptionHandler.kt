package com.livmas.data.handlers

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

abstract class NetworkCoroutineExceptionHandler: CoroutineExceptionHandler {
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        println("Exception occurred: $exception")
    }
}