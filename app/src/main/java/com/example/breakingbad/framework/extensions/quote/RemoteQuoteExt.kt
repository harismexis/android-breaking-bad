package com.example.breakingbad.framework.extensions.quote

import com.example.breakingbad.domain.Quote
import com.example.breakingbad.framework.datasource.network.model.RemoteQuote

fun List<RemoteQuote?>?.toItems(): List<Quote> {
    val items = mutableListOf<Quote>()
    if (this == null) return items.toList()
    val filteredList = this.filter { it?.quoteId != null }
    items.addAll(filteredList.map {
        it!!.toItem(it.quoteId!!)
    })
    return items.toList()
}

private fun RemoteQuote.toItem(id: Int): Quote {
    return Quote(
        id,
        this.quote,
        this.author,
        this.series
    )
}
