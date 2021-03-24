package com.harismexis.breakingbad.interactors.actor

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.domain.Actor

class IrrStoreActors(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(items: List<Actor>) = repository.insertActors(items)
}
