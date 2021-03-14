package com.example.breakingbad.interactors.death

import com.example.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteDeaths(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke() = repository.getDeaths()
}
