package com.harismexis.breakingbad.framework.data.network.repository

import com.harismexis.breakingbad.framework.data.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.data.network.model.toItems
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