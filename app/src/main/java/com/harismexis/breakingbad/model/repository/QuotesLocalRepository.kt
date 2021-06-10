package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.datasource.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.extensions.quote.toItems
import com.harismexis.breakingbad.framework.extensions.quote.toLocalItems
import com.harismexis.breakingbad.model.domain.Quote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesLocalRepository @Inject constructor(
    private val dao: BreakingBadLocalDao
) {
    suspend fun insertQuotes(items: List<Quote>) {
        dao.insertQuotes(items.toLocalItems())
    }

    suspend fun getQuotes(): List<Quote> {
        return dao.getAllQuotes().toItems()
    }

    suspend fun getQuotesBySeries(seriesName: String?): List<Quote> {
        return dao.getQuotesBySeries(seriesName).toItems()
    }

}