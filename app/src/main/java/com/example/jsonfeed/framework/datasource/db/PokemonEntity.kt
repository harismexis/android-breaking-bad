package com.example.jsonfeed.framework.datasource.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "imageUrlHiRes") val imageUrlHiRes: String?,
    @ColumnInfo(name = "supertype") val supertype: String?,
    @ColumnInfo(name = "subtype") val subtype: String?,
    @ColumnInfo(name = "artist") val artist: String?,
    @ColumnInfo(name = "rarity") val rarity: String?,
    @ColumnInfo(name = "series") val series: String?,
    @ColumnInfo(name = "set") val set: String?,
    @ColumnInfo(name = "setCode") val setCode: String?
)
