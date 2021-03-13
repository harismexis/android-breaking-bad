package com.example.breakingbad.interactors.actor

import com.example.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteActorsByName(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke(name: String?) = repository.getActorsByName(name)
}
