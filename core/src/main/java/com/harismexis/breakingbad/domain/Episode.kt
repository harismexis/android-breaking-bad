package com.harismexis.breakingbad.domain

import java.io.Serializable

data class Episode(
    val episode_id: Int,
    val title: String?,
    val season: String?,
    val air_date: String?,
    val characters: List<String>?,
    val episode: String?,
    val series: String?
) : Serializable