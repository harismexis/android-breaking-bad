package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

data class VideoItem(
    var videoId: String,
    var videoTitle: String,
    var isPlaying: Boolean = false
)