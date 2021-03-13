package com.example.breakingbad.interactors.quote

import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.domain.Quote

class IrrStoreQuotes(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(items: List<Quote>) = repository.insertQuotes(items)
}
