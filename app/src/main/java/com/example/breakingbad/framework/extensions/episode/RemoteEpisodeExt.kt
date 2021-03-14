package com.example.breakingbad.framework.extensions.episode

import com.example.breakingbad.domain.Episode
import com.example.breakingbad.domain.Quote
import com.example.breakingbad.framework.datasource.network.model.RemoteEpisode
import com.example.breakingbad.framework.datasource.network.model.RemoteQuote

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
