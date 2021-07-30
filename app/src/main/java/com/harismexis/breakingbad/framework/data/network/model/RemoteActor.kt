package com.harismexis.breakingbad.framework.data.network.model

import com.google.gson.annotations.SerializedName
import com.harismexis.breakingbad.model.domain.Actor

data class RemoteActor(
    @SerializedName("char_id")
    val actorId: Int?,
    val name: String?,
    val birthday: String?,
    var occupation: List<String>?,
    val img: String?,
    val status: String?,
    val nickname: String?,
    val portrayed: String?,
    val category: String?,
)

fun List<RemoteActor?>?.toItems(): List<Actor> {
    val items = mutableListOf<Actor>()
    if (this == null) return items.toList()
    val filteredList = this.filter { it?.actorId != null }
    items.addAll(filteredList.map {
        it !!.toItem(it.actorId !!)
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

