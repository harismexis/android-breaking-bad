package com.harismexis.breakingbad.framework.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harismexis.breakingbad.core.domain.Actor

@Entity(tableName = "actors_table")
data class LocalActor(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "birthday") val birthday: String?,
    @ColumnInfo(name = "occupation") val occupation: List<String>?,
    @ColumnInfo(name = "img") val img: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "nickname") val nickname: String?,
    @ColumnInfo(name = "portrayed") val portrayed: String?,
    @ColumnInfo(name = "category") val category: String?
)

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
        this.id,
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
        this.id,
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