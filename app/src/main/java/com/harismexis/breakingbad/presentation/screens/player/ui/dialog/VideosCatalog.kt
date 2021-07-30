package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

fun getFirstVideoId(): String {
    return getVideosList()[0].id
}

fun getVideosList(): List<Video> {
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
