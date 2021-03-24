package com.harismexis.breakingbad.interactors.death

import com.harismexis.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteDeaths(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke() = repository.getDeaths()
}
