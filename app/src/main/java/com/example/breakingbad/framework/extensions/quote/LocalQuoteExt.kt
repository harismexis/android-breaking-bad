package com.example.breakingbad.framework.extensions.quote

import com.example.breakingbad.domain.Quote
import com.example.breakingbad.framework.datasource.database.table.LocalQuote

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
