package com.harismexis.breakingbad.interactors.episode

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.domain.Episode

class IrrStoreEpisodes(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(items: List<Episode>) = repository.insertEpisodes(items)
}
