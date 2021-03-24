package com.harismexis.breakingbad.interactors.actor

import com.harismexis.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteActors(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke(name: String? = null) = repository.getActors(name)
}
