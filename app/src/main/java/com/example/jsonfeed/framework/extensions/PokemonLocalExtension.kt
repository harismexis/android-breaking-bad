package com.example.jsonfeed.framework.extensions

import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.datasource.db.PokemonEntity

fun List<PokemonEntity?>?.toItems(): List<Item> {
    val items = mutableListOf<Item>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun PokemonEntity.toItem(): Item {
    return Item(
        this.id,
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

fun List<Item?>?.toPokemonEntities(): List<PokemonEntity> {
    val pokemonEntities = mutableListOf<PokemonEntity>()
    if (this == null) return pokemonEntities.toList()
    val filteredList = this.filterNotNull()
    pokemonEntities.addAll(filteredList.map {
        it.toPokemonEntity()
    })
    return pokemonEntities.toList()
}

fun Item.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        this.id,
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
