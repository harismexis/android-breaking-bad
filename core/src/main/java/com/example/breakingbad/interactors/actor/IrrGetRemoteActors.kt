package com.example.breakingbad.interactors.actor

import com.example.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteActors(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke(name: String? = null) = repository.getActors(name)
}
