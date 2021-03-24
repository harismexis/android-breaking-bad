package com.harismexis.breakingbad.interactors.death

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.domain.Death

class IrrStoreDeaths(
    private val repository: BreakingBadLocalRepository
) {
    suspend operator fun invoke(items: List<Death>) = repository.insertDeaths(items)
}
