package com.example.breakingbad.interactors.episode

import com.example.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteEpisodes(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke() = repository.getEpisodes()
}
