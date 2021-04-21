package com.harismexis.breakingbad.framework.extensions.episode

import com.harismexis.breakingbad.datamodel.domain.Episode
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteEpisode

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
