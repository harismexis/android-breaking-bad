package com.harismexis.breakingbad.framework.extensions.quote

import com.harismexis.breakingbad.model.domain.Quote
import com.harismexis.breakingbad.model.datasource.network.model.RemoteQuote

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
