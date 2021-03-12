package com.example.breakingbad.framework.extensions

import com.example.breakingbad.framework.datasource.database.BBCharacterLocalItem
import com.example.breakingbad.domain.BBCharacter

fun List<BBCharacterLocalItem?>?.toItems(): List<BBCharacter> {
    val items = mutableListOf<BBCharacter>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun BBCharacterLocalItem.toItem(): BBCharacter {
    return BBCharacter(
        this.id,
        this.name,
        this.temperament,
        this.origin,
        this.description,
        this.lifeSpan,
        this.energyLevel,
        this.wikipediaUrl,
        this.imageUrl
    )
}

fun List<BBCharacter?>?.toLocalItems(): List<BBCharacterLocalItem> {
    val localItems = mutableListOf<BBCharacterLocalItem>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun BBCharacter.toLocalItem(): BBCharacterLocalItem {
    return BBCharacterLocalItem(
        this.id,
        this.name,
        this.temperament,
        this.origin,
        this.description,
        this.lifeSpan,
        this.energyLevel,
        this.wikipediaUrl,
        this.imageUrl
    )
}
