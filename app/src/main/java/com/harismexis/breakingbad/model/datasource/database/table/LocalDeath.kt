package com.harismexis.breakingbad.model.datasource.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
