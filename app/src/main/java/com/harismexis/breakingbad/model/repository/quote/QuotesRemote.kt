package com.harismexis.breakingbad.model.repository.quote

import com.harismexis.breakingbad.model.domain.Quote

interface QuotesRemote {

    suspend fun getQuotes(series: String? = null): List<Quote>

}