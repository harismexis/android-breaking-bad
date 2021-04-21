package com.harismexis.breakingbad.presentation.screens.quotes.model

import com.harismexis.breakingbad.datamodel.domain.Quote
import com.harismexis.breakingbad.datamodel.repo.QuoteLocalRepo
import com.harismexis.breakingbad.datamodel.repo.QuoteRemoteRepo
import javax.inject.Inject

data class QuotesModel @Inject constructor (
    private val quoteRemote: QuoteRemoteRepo,
    private val quoteLocal: QuoteLocalRepo
) {
    suspend fun getRemoteQuotes(series: String? = null): List<Quote> = quoteRemote.getQuotes(series)

    suspend fun insertQuotes(items: List<Quote>) {
        quoteLocal.insertQuotes(items)
    }

    suspend fun getLocalQuotes(): List<Quote> {
        return quoteLocal.getQuotes()
    }

    suspend fun getQuotesBySeries(seriesName: String?): List<Quote> {
        return quoteLocal.getQuotesBySeries(seriesName)
    }
}