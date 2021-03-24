package com.harismexis.breakingbad.interactors.episode

import com.harismexis.breakingbad.data.BreakingBadRemoteRepository

class IrrGetRemoteEpisodes(
    private val repository: BreakingBadRemoteRepository
) {
    suspend operator fun invoke() = repository.getEpisodes()
}
