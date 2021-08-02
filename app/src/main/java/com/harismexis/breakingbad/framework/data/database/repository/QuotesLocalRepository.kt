package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.core.domain.Quote
import com.harismexis.breakingbad.core.repository.quote.QuotesLocal
import com.harismexis.breakingbad.framework.data.database.dao.QuotesDao
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotesLocalRepository @Inject constructor(
    private val dao: QuotesDao
) : QuotesLocal {

    override suspend fun insertQuotes(items: List<Quote>) {
        dao.delete()
        dao.insert(items.toLocalItems())
    }

    override suspend fun getQuotes(): List<Quote> {
        return dao.getAll().toItems()
    }

    override suspend fun getQuotesBySeries(seriesName: String?): List<Quote> {
        return dao.getBySeries(seriesName).toItems()
    }

}