package com.example.breakingbad.interactors.episode

import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.domain.Episode
import com.example.breakingbad.domain.Quote

class IrrStoreEpisodes(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(items: List<Episode>) = repository.insertEpisodes(items)
}
