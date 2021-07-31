package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.framework.data.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import com.harismexis.breakingbad.core.domain.Death
import com.harismexis.breakingbad.core.repository.death.DeathsLocal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeathsLocalRepository @Inject constructor(
    private val dao: BreakingBadLocalDao
): DeathsLocal {

    override suspend fun insertDeaths(items: List<Death>) {
        dao.insertDeaths(items.toLocalItems())
    }

    override suspend fun getDeaths(): List<Death> {
        return dao.getAllDeaths().toItems()
    }

}