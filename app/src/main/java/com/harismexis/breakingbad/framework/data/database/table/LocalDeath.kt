package com.harismexis.breakingbad.framework.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harismexis.breakingbad.core.domain.Death

@Entity(tableName = "death_table")
data class LocalDeath(
    @PrimaryKey @ColumnInfo(name = "death_id") val death_id: Int,
    @ColumnInfo(name = "death") val death: String?,
    @ColumnInfo(name = "cause") val cause: String?,
    @ColumnInfo(name = "responsible") val responsible: String?,
    @ColumnInfo(name = "last_words") val last_words: String?,
    @ColumnInfo(name = "season") val season: Int?,
    @ColumnInfo(name = "episode") val episode: Int?,
    @ColumnInfo(name = "number_of_deaths") val number_of_deaths: Int?
)

fun List<LocalDeath?>?.toItems(): List<Death> {
    val items = mutableListOf<Death>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalDeath.toItem(): Death {
    return Death(
        this.death_id,
        this.death,
        this.cause,
        this.responsible,
        this.last_words,
        this.season,
        this.episode,
        this.number_of_deaths
    )
}

fun List<Death?>?.toLocalItems(): List<LocalDeath> {
    val localItems = mutableListOf<LocalDeath>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Death.toLocalItem(): LocalDeath {
    return LocalDeath(
        this.death_id,
        this.death,
        this.cause,
        this.responsible,
        this.last_words,
        this.season,
        this.episode,
        this.number_of_deaths
    )
}

