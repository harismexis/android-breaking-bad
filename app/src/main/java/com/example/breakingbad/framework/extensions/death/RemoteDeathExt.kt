package com.example.breakingbad.framework.extensions.death

import com.example.breakingbad.domain.Death
import com.example.breakingbad.framework.datasource.network.model.RemoteDeath

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