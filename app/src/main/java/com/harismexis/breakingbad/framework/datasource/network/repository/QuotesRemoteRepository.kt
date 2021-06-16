package com.harismexis.breakingbad.framework.datasource.network.repository

import com.harismexis.breakingbad.framework.datasource.network.dao.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.quote.toItems
import com.harismexis.breakingbad.model.domain.Quote
import com.harismexis.breakingbad.model.repository.QuotesRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class QuotesRemoteRepository @Inject constructor(
    private val dao: BreakingBadRemoteDao
): QuotesRemote {
    override suspend fun getQuotes(series: String?): List<Quote> = dao.getQuotes(series).toItems()

}