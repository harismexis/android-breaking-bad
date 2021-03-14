package com.example.breakingbad.interactors.death

import com.example.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalDeaths(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getDeaths()
}
