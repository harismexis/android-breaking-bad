package com.example.breakingbad.framework.extensions

import com.example.breakingbad.framework.datasource.database.BBCharacterLocal
import com.example.breakingbad.domain.BBCharacter

fun List<BBCharacterLocal?>?.toItems(): List<BBCharacter> {
    val items = mutableListOf<BBCharacter>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun BBCharacterLocal.toItem(): BBCharacter {
    return BBCharacter(
        this.char_id,
        this.name,
        this.birthday,
        this.img,
        this.status,
        this.nickname,
        this.portrayed,
        this.category
    )
}

fun List<BBCharacter?>?.toLocalItems(): List<BBCharacterLocal> {
    val localItems = mutableListOf<BBCharacterLocal>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun BBCharacter.toLocalItem(): BBCharacterLocal {
    return BBCharacterLocal(
        this.char_id!!,
        this.name,
        this.birthday,
        this.img,
        this.status,
        this.nickname,
        this.portrayed,
        this.category
    )
}
