package com.example.breakingbad.interactors.quote

import com.example.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalQuotes(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getQuotes()
}
