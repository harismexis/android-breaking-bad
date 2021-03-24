package com.harismexis.breakingbad.interactors.actor

import com.harismexis.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalActors(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getActors()
}
