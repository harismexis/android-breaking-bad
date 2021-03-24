package com.harismexis.breakingbad.interactors.quote

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.domain.Quote

class IrrStoreQuotes(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(items: List<Quote>) = repository.insertQuotes(items)
}
