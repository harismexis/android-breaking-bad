package com.example.breakingbad.interactors.quote

import com.example.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteQuotes(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke() = repository.getQuotes()
}
