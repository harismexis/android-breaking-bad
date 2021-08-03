package com.harismexis.breakingbad.core.repository.quote

import com.harismexis.breakingbad.core.domain.Quote

interface QuotesLocal {

    suspend fun save(items: List<Quote>)

    suspend fun getQuotes(): List<Quote>

    suspend fun getQuotesBySeries(seriesName: String?): List<Quote>
}