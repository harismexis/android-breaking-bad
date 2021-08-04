package com.harismexis.breakingbad.framework.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harismexis.breakingbad.core.domain.Video

@Entity(tableName = "videos_table")
data class LocalVideo(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "season") val season: Int?,
    @ColumnInfo(name = "episode") val episode: Int?
)

fun List<LocalVideo?>?.toItems(): List<Video> {
    val items = mutableListOf<Video>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalVideo.toItem(): Video {
    return Video(
        this.id,
        this.title,
        this.season,
        this.episode
    )
}

fun List<Video?>?.toLocalItems(): List<LocalVideo> {
    val localItems = mutableListOf<LocalVideo>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Video.toLocalItem(): LocalVideo {
    return LocalVideo(
        this.id,
        this.title,
        this.season,
        this.episode
    )
}