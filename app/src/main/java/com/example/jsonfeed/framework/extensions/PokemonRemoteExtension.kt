package com.example.jsonfeed.framework.extensions

import com.example.jsonfeed.framework.datasource.network.PokemonFeed
import com.example.jsonfeed.framework.datasource.network.PokemonCard
import com.example.jsonfeed.domain.Item

fun PokemonFeed?.toItems(): List<Item> {
    val items = mutableListOf<Item>()
    if (this == null) return items.toList()
    val remoteItems = this.cards ?: return items.toList()
    val filteredList = remoteItems.filter { it != null && !it.id.isNullOrBlank() }
    items.addAll(filteredList.map {
        it!!.toItem(it.id!!)
    })
    return items.toList()
}

private fun PokemonCard.toItem(id: String): Item {
    return Item(
        id,
        this.name,
        this.imageUrl,
        this.imageUrlHiRes,
        this.supertype,
        this.subtype,
        this.artist,
        this.rarity,
        this.series,
        this.set,
        this.setCode
    )
}
