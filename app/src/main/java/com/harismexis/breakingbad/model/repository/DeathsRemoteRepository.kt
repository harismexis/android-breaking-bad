package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.domain.Death
import com.harismexis.breakingbad.model.datasource.network.dao.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.death.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeathsRemoteRepository @Inject constructor(
    private val dao: BreakingBadRemoteDao
) {
    suspend fun getDeaths(): List<Death> = dao.getDeaths().toItems()

}