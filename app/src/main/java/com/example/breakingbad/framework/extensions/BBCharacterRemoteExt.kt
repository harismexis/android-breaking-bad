package com.example.breakingbad.framework.extensions

import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemoteItem
import com.example.breakingbad.domain.BBCharacter

fun List<BBCharacterRemoteItem?>?.toItems(): List<BBCharacter> {
    val items = mutableListOf<BBCharacter>()
    if (this == null) return items.toList()
    val filteredList = this.filter { it != null && !it.id.isNullOrBlank() }
    items.addAll(filteredList.map {
        it!!.toItem(it.id!!)
    })
    return items.toList()
}

private fun BBCharacterRemoteItem.toItem(id: String): BBCharacter {
    return BBCharacter(
        id,
        this.name,
        this.temperament,
        this.origin,
        this.description,
        this.lifeSpan,
        this.energyLevel,
        this.wikipediaUrl,
        this.image?.url
    )
}
