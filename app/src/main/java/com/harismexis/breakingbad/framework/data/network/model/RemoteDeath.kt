package com.harismexis.breakingbad.framework.data.network.model

import com.google.gson.annotations.SerializedName
import com.harismexis.breakingbad.core.domain.Death

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

fun List<RemoteDeath?>?.toItems(): List<Death> {
    val items = mutableListOf<Death>()
    if (this == null) return items.toList()
    val filteredList = this.filter { it?.deathId != null }
    items.addAll(filteredList.map {
        it!!.toItem(it.deathId!!)
    })
    return items.toList()
}

private fun RemoteDeath.toItem(id: Int): Death {
    return Death(
        id,
        this.death,
        this.cause,
        this.responsible,
        this.lastWords,
        this.season,
        this.episode,
        this.numberOfDeaths
    )
}
