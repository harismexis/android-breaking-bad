package com.harismexis.breakingbad.datamodel.repo

import com.harismexis.breakingbad.datamodel.domain.Death
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.death.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeathRemoteRepo @Inject constructor(
    private val dao: BreakingBadRemoteDao
) {
    suspend fun getDeaths(): List<Death> = dao.getDeaths().toItems()

}