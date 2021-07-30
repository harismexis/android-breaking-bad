package com.harismexis.breakingbad.framework.data.network.repository

import com.harismexis.breakingbad.framework.data.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.data.network.model.toItems
import com.harismexis.breakingbad.model.domain.Quote
import com.harismexis.breakingbad.model.repository.QuotesRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class QuotesRemoteRepository @Inject constructor(
    private val api: BreakingBadApi
): QuotesRemote {

    override suspend fun getQuotes(series: String?): List<Quote> {
        return api.getQuotes(series).toItems()
    }

}