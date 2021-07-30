package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

data class Video(
    var id: String,
    var title: String,
    var season: Int?,
    var episode: Int?,
    var isPlaying: Boolean = false
) {
    constructor(id: String, title: String) : this(id, title, null, null) {

    }
}