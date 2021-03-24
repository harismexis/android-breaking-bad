package com.harismexis.breakingbad.interactors.death

import com.harismexis.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalDeaths(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getDeaths()
}
