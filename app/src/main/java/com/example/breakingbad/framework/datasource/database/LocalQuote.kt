package com.example.breakingbad.framework.datasource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class LocalQuote(
    @PrimaryKey @ColumnInfo(name = "quote_id") val quote_id: Int,
    @ColumnInfo(name = "quote") val quote: String?,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "series") val series: String?
)
