package com.example.breakingbad.interactors

import com.example.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalActors(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getActors()
}
