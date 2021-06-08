package com.harismexis.breakingbad.presentation.screens.player.videosdialog

data class Video(
    var id: String,
    var title: String,
    var season: Int?,
    var episode: Int?
) {
    constructor(id: String, title: String) : this(id, title, null, null) {

    }
}