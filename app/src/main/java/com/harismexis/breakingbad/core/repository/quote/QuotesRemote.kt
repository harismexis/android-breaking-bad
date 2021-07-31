package com.harismexis.breakingbad.core.repository.quote

import com.harismexis.breakingbad.core.domain.Quote

interface QuotesRemote {

    suspend fun getQuotes(series: String? = null): List<Quote>

}