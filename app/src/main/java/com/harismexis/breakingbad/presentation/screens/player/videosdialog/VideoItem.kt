package com.harismexis.breakingbad.presentation.screens.player.videosdialog

data class VideoItem(
    var videoId: String,
    var videoTitle: String,
    var isPlaying: Boolean = false
)