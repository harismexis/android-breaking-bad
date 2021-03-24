package com.harismexis.breakingbad.interactors.actor

import com.harismexis.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalActor(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(itemId: Int) = repository.getActor(itemId)
}
