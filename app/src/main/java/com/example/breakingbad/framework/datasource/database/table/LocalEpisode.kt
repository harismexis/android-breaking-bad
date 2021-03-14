package com.example.breakingbad.framework.datasource.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_table")
data class LocalEpisode(
    @PrimaryKey @ColumnInfo(name = "episode_id") val episode_id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "season") val season: String?,
    @ColumnInfo(name = "air_date") val air_date: String?,
    @ColumnInfo(name = "characters") val characters: List<String>?,
    @ColumnInfo(name = "episode") val episode: String?,
    @ColumnInfo(name = "series") val series: String?
)
