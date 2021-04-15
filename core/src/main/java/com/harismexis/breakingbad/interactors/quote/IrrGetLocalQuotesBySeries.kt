package com.harismexis.breakingbad.interactors.quote

import com.harismexis.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalQuotesBySeries(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(seriesName: String?) = repository.getQuotesBySeries(seriesName)
}
