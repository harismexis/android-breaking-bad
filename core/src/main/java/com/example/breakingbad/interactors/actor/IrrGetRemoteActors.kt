package com.example.breakingbad.interactors.actor

import com.example.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteActors(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke() = repository.getActors()
}
