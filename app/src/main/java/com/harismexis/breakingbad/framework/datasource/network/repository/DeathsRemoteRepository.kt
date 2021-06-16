package com.harismexis.breakingbad.framework.datasource.network.repository

import com.harismexis.breakingbad.framework.extensions.death.toItems
import com.harismexis.breakingbad.framework.datasource.network.dao.BreakingBadRemoteDao
import com.harismexis.breakingbad.model.domain.Death
import com.harismexis.breakingbad.model.repository.DeathsRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeathsRemoteRepository @Inject constructor(
    private val dao: BreakingBadRemoteDao
): DeathsRemote {
    override suspend fun getDeaths(): List<Death> = dao.getDeaths().toItems()

}