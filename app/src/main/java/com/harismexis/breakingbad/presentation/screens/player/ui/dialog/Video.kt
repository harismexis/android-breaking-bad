package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

data class Video(
    val id: String,
    val title: String,
    val season: Int?,
    val episode: Int?,
    var isPlaying: Boolean = false
) {
    constructor(id: String, title: String) : this(id, title, null, null)
}