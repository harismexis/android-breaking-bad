package com.example.breakingbad.interactors.death

import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.domain.Death

class IrrStoreDeaths(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(items: List<Death>) = repository.insertDeaths(items)
}
