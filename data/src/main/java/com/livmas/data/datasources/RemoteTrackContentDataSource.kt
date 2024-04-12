package com.livmas.data.datasources

class RemoteTrackContentDataSource {
    fun getTrackURLById(id: Long): String {
        return "https://pro13.easy4.team/segments/output.m3u8"
    }
}
