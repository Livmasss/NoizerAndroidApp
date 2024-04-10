package com.livmas.data.datasources

import com.livmas.data.models.TrackModel

internal class RemoteTrackInfoDataSource {
    fun getTracks(): List<TrackModel> {
        return listOf(
            TrackModel(
                0,
                "Tsaritsa",
                "ANNA ASTI",
                "https://t2.genius.com/unsafe/504x504/https%3A%2F%2Fimages.genius.com%2F151d8f514655fbc49960bc43c377bc6e.1000x1000x1.png",
                false
                ),
            TrackModel(
                1,
                "Стужа",
                "Пятерка",
                "https://t2.genius.com/unsafe/504x504/https%3A%2F%2Fimages.genius.com%2F71ab5e17e28907ba5298720dc8f9c985.1000x1000x1.png",
                false
            ),
            TrackModel(
                2,
                "LIPSI HA",
                "Instasamka",
                "https://t2.genius.com/unsafe/504x504/https%3A%2F%2Fimages.genius.com%2F0fcdc3a7595c08c0a3931f12ac2165ec.873x873x1.jpg",
                false
            )
        )
    }
}
