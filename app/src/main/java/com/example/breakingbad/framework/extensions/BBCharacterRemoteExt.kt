package com.example.breakingbad.framework.extensions

import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemote
import com.example.breakingbad.domain.BBCharacter

fun List<BBCharacterRemote?>?.toItems(): List<BBCharacter> {
    val items = mutableListOf<BBCharacter>()
    if (this == null) return items.toList()
    val filteredList = this.filter { it?.char_id != null }
    items.addAll(filteredList.map {
        it !!.toItem(it.char_id !!)
    })
    return items.toList()
}

private fun BBCharacterRemote.toItem(id: Int): BBCharacter {
    return BBCharacter(
        id,
        this.name,
        this.birthday,
        this.img,
        this.status,
        this.nickname,
        this.portrayed,
        this.category
    )
}
