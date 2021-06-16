package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.domain.Quote

interface QuotesRemote {

    suspend fun getQuotes(series: String? = null): List<Quote>

}