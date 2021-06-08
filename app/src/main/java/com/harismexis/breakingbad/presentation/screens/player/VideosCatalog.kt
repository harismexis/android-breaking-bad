package com.harismexis.breakingbad.presentation.screens.player

import com.harismexis.breakingbad.presentation.screens.player.videosdialog.Video
import com.harismexis.breakingbad.presentation.screens.player.videosdialog.VideoItem

object VideosCatalog {

    private fun getVideos(): List<Video> {
        val videos = ArrayList<Video>()
        videos.add(
            Video(
                "QmHCn5xXHjI",
                "Breaking Bad Blooper Reel"
            )
        )
        videos.add(
            Video(
                "zzyudpED6sg",
                "Every death in Breaking Bad"
            )
        )
        videos.add(
            Video(
                "Xs6_vecSv2Y",
                "Breaking Bad Greatest Moments"
            )
        )
        videos.add(
            Video(
                "c9BW9dCVYzA",
                "Hank and Walt"
            )
        )
        videos.add(
            Video(
                "pgxFTghg0lI",
                "Breaking Bad Season 3: Episode 7: Killing the Twins HD CLIP"
            )
        )
        videos.add(
            Video(
                "6P7XzoTJRa8",
                "Breaking Bad Season 5: Episode 14: The phone call HD CLIP"
            )
        )
        videos.add(
            Video(
                "QLUG7jSUR08",
                "Breaking Bad Season 5: Episode 7: Walt kills Mike HD CLIP"
            )
        )
        videos.add(
            Video(
                "5wOvXusjqgI",
                "Breaking Bad Season 5: Episode 9: The confrontation HD CLIP"
            )
        )
        videos.add(
            Video(
                "8-aOkHmo-_c",
                "Breaking Bad Season 5: Episode 10: That money thing HD CLIP"
            )
        )
        return videos
    }

    fun getVideoItems(): List<VideoItem> {
        return getVideos().map { VideoItem(it.id, it.title) }
    }

}