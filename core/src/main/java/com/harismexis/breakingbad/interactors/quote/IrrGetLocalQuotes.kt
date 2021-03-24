package com.harismexis.breakingbad.interactors.quote

import com.harismexis.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalQuotes(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getQuotes()
}
