package com.example.breakingbad.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class RemoteDeath(
    @SerializedName("death_id")
    val deathId: Int?,
    val death: String?,
    val cause: String?,
    val responsible: String?,
    @SerializedName("last_words")
    val lastWords: String?,
    val season: Int?,
    val episode: Int?,
    @SerializedName("number_of_deaths")
    val numberOfDeaths: Int?
)
