package com.example.breakingbad.framework.extensions

import com.example.breakingbad.framework.datasource.database.BBActorLocal
import com.example.breakingbad.domain.BBActor

fun List<BBActorLocal?>?.toItems(): List<BBActor> {
    val items = mutableListOf<BBActor>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun BBActorLocal.toItem(): BBActor {
    return BBActor(
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

fun List<BBActor?>?.toLocalItems(): List<BBActorLocal> {
    val localItems = mutableListOf<BBActorLocal>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun BBActor.toLocalItem(): BBActorLocal {
    return BBActorLocal(
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
