package com.example.breakingbad.framework.datasource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breaking_bad_character_table")
data class BBCharacterLocalItem(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "temperament") val temperament: String?,
    @ColumnInfo(name = "origin") val origin: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "life_span") val lifeSpan: String?,
    @ColumnInfo(name = "energy_level") val energyLevel: Int?,
    @ColumnInfo(name = "wikipedia_url") val wikipediaUrl: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?
)
