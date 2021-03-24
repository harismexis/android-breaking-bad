package com.harismexis.breakingbad.interactors.episode

import com.harismexis.breakingbad.data.BreakingBadLocalRepository

class IrrGetLocalEpisodes(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke() = repository.getEpisodes()
}
