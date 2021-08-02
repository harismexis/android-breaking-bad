package com.harismexis.breakingbad.framework.data.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harismexis.breakingbad.core.domain.Quote

@Entity(tableName = "quotes_table")
data class LocalQuote(
    @PrimaryKey @ColumnInfo(name = "quote_id") val quote_id: Int,
    @ColumnInfo(name = "quote") val quote: String?,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "series") val series: String?
)

fun List<LocalQuote?>?.toItems(): List<Quote> {
    val items = mutableListOf<Quote>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalQuote.toItem(): Quote {
    return Quote(
        this.quote_id,
        this.quote,
        this.author,
        this.series
    )
}

fun List<Quote?>?.toLocalItems(): List<LocalQuote> {
    val localItems = mutableListOf<LocalQuote>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Quote.toLocalItem(): LocalQuote {
    return LocalQuote(
        this.quote_id,
        this.quote,
        this.author,
        this.series
    )
}
