package com.livmas.data.apis

import com.livmas.data.dataModule
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class TrackInfoApiTest: KoinTest {

    private val retrofit: Retrofit by inject()
    private val api: TrackInfoApi = retrofit.create(TrackInfoApi::class.java)

    @Test
    fun testTracksFeed() {
        val feedResponse = api.getTracksFeed().execute()
        assertTrue { feedResponse.isSuccessful }
        val feedResult = feedResponse.body()

        assertNotNull(feedResult)
    }

    @Test
    fun testSearchTracks_founded() {
        val feedResponse = api.findTracksByQuery("a").execute()
        assertTrue { feedResponse.isSuccessful }
        val feedResult = feedResponse.body()

        assertNotNull(feedResult)
    }

    @Test
    fun testSearchTracks_notFounded() {
        val feedResponse = api.findTracksByQuery("123").execute()
        assertTrue { feedResponse.isSuccessful }
        val feedResult = feedResponse.body()

        assertNotNull(feedResult)
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun startKoin() {
            startKoin {
                modules(
                    dataModule
                )
            }
        }
    }
}