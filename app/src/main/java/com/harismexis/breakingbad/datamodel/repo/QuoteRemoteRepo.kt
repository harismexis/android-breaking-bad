package com.harismexis.breakingbad.datamodel.repo

import com.harismexis.breakingbad.datamodel.domain.Quote
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.quote.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class QuoteRemoteRepo @Inject constructor(
    private val dao: BreakingBadRemoteDao
) {
    suspend fun getQuotes(series: String? = null): List<Quote> = dao.getQuotes(series).toItems()

}