package com.livmas.data.repositories

import com.livmas.data.datasources.RemoteMediaDataSource
import com.livmas.data.datasources.RemoteTrackInfoDataSource
import com.livmas.data.models.TrackModel
import com.livmas.util.domain.models.TrackDTO
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

internal class RemoteTrackRepositoryImplTest {
    private val remoteTrackInfoDataSource: RemoteTrackInfoDataSource = mockk()
    private val remoteMediaDataSource: RemoteMediaDataSource = mockk()

    private val repository = RemoteTrackRepositoryImpl(
        remoteTrackInfoDataSource, remoteMediaDataSource
    )

    private val tracks = listOf(TrackModel(
        1,
        "Test",
        "Author",
        ""
    ))
    private val trackUri = "http://www.pierobon.org/iis/review1.htm.html#one"
    @Test
    fun searchTracksTest() {
        every { remoteTrackInfoDataSource.findTracks("Test") } returns tracks

        val result = repository.searchTracks("Test")
        assertEquals(tracks[0].title, result[0].title)
        assertEquals(tracks[0].author, result[0].author)
    }

    @Test
    fun getTracksTest() {
        every { remoteTrackInfoDataSource.getTracks() } returns tracks

        val result = repository.getTracks()
        assertEquals(tracks[0].title, result[0].title)
        assertEquals(tracks[0].author, result[0].author)
    }

    @Test
    fun getTrackUriTest() {
        every { remoteMediaDataSource.getTrackURLById(1) } returns trackUri

        val result = repository.getTrackURLById(1)
        assertEquals(trackUri, result)
    }
}