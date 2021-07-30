package com.harismexis.breakingbad.model.domain

data class Death(
    val death_id: Int,
    val death: String?,
    val cause: String?,
    val responsible: String?,
    val last_words: String?,
    val season: Int?,
    val episode: Int?,
    val number_of_deaths: Int?
)