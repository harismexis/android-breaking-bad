package com.harismexis.breakingbad.model.repository.quote

import com.harismexis.breakingbad.model.domain.Quote

interface QuotesLocal {

    suspend fun insertQuotes(items: List<Quote>)

    suspend fun getQuotes(): List<Quote>

    suspend fun getQuotesBySeries(seriesName: String?): List<Quote>

}