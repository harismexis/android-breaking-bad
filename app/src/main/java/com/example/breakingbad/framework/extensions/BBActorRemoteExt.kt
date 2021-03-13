package com.example.breakingbad.framework.extensions

import com.example.breakingbad.framework.datasource.network.model.BBActorRemote
import com.example.breakingbad.domain.BBActor

fun List<BBActorRemote?>?.toItems(): List<BBActor> {
    val items = mutableListOf<BBActor>()
    if (this == null) return items.toList()
    val filteredList = this.filter { it?.char_id != null }
    items.addAll(filteredList.map {
        it !!.toItem(it.char_id !!)
    })
    return items.toList()
}

private fun BBActorRemote.toItem(id: Int): BBActor {
    return BBActor(
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
