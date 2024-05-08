package com.livmas.player.data.datasource_factories

import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource

@UnstableApi
internal class MyBaseDataSourceFactory(
    private val context: Context
): DataSource.Factory {
    override fun createDataSource(): DataSource =
        DefaultDataSource(
            context,
            true
        )
}