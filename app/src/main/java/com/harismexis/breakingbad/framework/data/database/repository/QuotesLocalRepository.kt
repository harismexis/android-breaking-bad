package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.framework.data.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import com.harismexis.breakingbad.core.domain.Quote
import com.harismexis.breakingbad.core.repository.quote.QuotesLocal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesLocalRepository @Inject constructor(
    private val dao: BreakingBadLocalDao
): QuotesLocal {
    override suspend fun insertQuotes(items: List<Quote>) {
        dao.insertQuotes(items.toLocalItems())
    }

    override suspend fun getQuotes(): List<Quote> {
        return dao.getAllQuotes().toItems()
    }

    override suspend fun getQuotesBySeries(seriesName: String?): List<Quote> {
        return dao.getQuotesBySeries(seriesName).toItems()
    }

}