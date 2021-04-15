package com.harismexis.breakingbad.framework.extensions.actor

import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteActor

fun List<RemoteActor?>?.toItems(): List<Actor> {
    val items = mutableListOf<Actor>()
    if (this == null) return items.toList()
    val filteredList = this.filter { it?.char_id != null }
    items.addAll(filteredList.map {
        it !!.toItem(it.char_id !!)
    })
    return items.toList()
}

private fun RemoteActor.toItem(id: Int): Actor {
    return Actor(
        id,
        this.name,
        this.birthday,
        this.occupation,
        this.img,
        this.status,
        this.nickname,
        this.portrayed,
        this.category
    )
}
