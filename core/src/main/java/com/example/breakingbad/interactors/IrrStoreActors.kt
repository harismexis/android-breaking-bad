package com.example.breakingbad.interactors

import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.domain.Actor

class IrrStoreActors(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(items: List<Actor>) = repository.insertActors(items)
}
