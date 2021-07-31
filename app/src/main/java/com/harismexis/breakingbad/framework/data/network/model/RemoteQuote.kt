package com.harismexis.breakingbad.framework.data.network.model

import com.google.gson.annotations.SerializedName
import com.harismexis.breakingbad.core.domain.Quote

data class RemoteQuote(
    @SerializedName("quote_id")
    val quoteId: Int?,
    val quote: String?,
    val author: String?,
    val series: String?
)

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
