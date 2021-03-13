package com.example.breakingbad.interactors.actor

import com.example.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalActors(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getActors()
}
