package com.harismexis.breakingbad.interactors.quote

import com.harismexis.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteQuotes(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke(series: String? = null) = repository.getQuotes(series)
}
