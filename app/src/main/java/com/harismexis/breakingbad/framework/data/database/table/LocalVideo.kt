package com.harismexis.breakingbad.framework.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos_table")
data class LocalVideo(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "season") val season: Int?,
    @ColumnInfo(name = "episode") val episode: Int?
)