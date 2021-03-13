package com.example.breakingbad.framework.extensions.actor

import com.example.breakingbad.framework.datasource.database.LocalActor
import com.example.breakingbad.domain.Actor

fun List<LocalActor?>?.toItems(): List<Actor> {
    val items = mutableListOf<Actor>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalActor.toItem(): Actor {
    return Actor(
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

fun List<Actor?>?.toLocalItems(): List<LocalActor> {
    val localItems = mutableListOf<LocalActor>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Actor.toLocalItem(): LocalActor {
    return LocalActor(
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
