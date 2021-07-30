package com.harismexis.breakingbad.framework.datasource.network.repository

import com.harismexis.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.extensions.death.toItems
import com.harismexis.breakingbad.model.domain.Death
import com.harismexis.breakingbad.model.repository.DeathsRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeathsRemoteRepository @Inject constructor(
    private val api: BreakingBadApi
): DeathsRemote {

    override suspend fun getDeaths(): List<Death> {
        return api.getDeaths().toItems()
    }

}