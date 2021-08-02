package com.harismexis.breakingbad.framework.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harismexis.breakingbad.core.domain.Episode

@Entity(tableName = "episodes_table")
data class LocalEpisode(
    @PrimaryKey @ColumnInfo(name = "episode_id") val episode_id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "season") val season: String?,
    @ColumnInfo(name = "air_date") val air_date: String?,
    @ColumnInfo(name = "characters") val characters: List<String>?,
    @ColumnInfo(name = "episode") val episode: String?,
    @ColumnInfo(name = "series") val series: String?
)

fun List<LocalEpisode?>?.toItems(): List<Episode> {
    val items = mutableListOf<Episode>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalEpisode.toItem(): Episode {
    return Episode(
        this.episode_id,
        this.title,
        this.season,
        this.air_date,
        this.characters,
        this.episode,
        this.series
    )
}

fun List<Episode?>?.toLocalItems(): List<LocalEpisode> {
    val localItems = mutableListOf<LocalEpisode>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Episode.toLocalItem(): LocalEpisode {
    return LocalEpisode(
        this.episode_id,
        this.title,
        this.season,
        this.air_date,
        this.characters,
        this.episode,
        this.series
    )
}
