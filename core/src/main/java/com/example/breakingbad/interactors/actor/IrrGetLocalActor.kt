package com.example.breakingbad.interactors.actor

import com.example.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalActor(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(itemId: Int) = repository.getActor(itemId)
}
