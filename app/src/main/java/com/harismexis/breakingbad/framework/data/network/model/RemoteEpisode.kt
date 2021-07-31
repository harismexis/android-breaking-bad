package com.harismexis.breakingbad.framework.data.network.model

import com.google.gson.annotations.SerializedName
import com.harismexis.breakingbad.core.domain.Episode

data class RemoteEpisode(
    @SerializedName("episode_id")
    val episodeId: Int?,
    val title: String?,
    val season: String?,
    @SerializedName("air_date")
    val airDate: String?,
    val characters: List<String>?,
    val episode: String?,
    val series: String?
)

fun List<RemoteEpisode?>?.toItems(): List<Episode> {
    val items = mutableListOf<Episode>()
    if (this == null) return items.toList()
    val filteredList = this.filter { it?.episodeId != null }
    items.addAll(filteredList.map {
        it!!.toItem(it.episodeId!!)
    })
    return items.toList()
}

private fun RemoteEpisode.toItem(id: Int): Episode {
    return Episode(
        id,
        this.title,
        this.season,
        this.airDate,
        this.characters,
        this.episode,
        this.series
    )
}
