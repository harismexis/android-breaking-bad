package com.example.breakingbad.interactors.episode

import com.example.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalEpisodes(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getEpisodes()
}
