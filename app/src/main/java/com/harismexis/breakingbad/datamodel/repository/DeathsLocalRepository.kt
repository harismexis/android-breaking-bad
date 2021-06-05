package com.harismexis.breakingbad.datamodel.repository

import com.harismexis.breakingbad.datamodel.domain.Death
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.extensions.death.toItems
import com.harismexis.breakingbad.framework.extensions.death.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeathsLocalRepository @Inject constructor(
    private val dao: BreakingBadLocalDao
) {
    suspend fun insertDeaths(items: List<Death>) {
        dao.insertDeaths(items.toLocalItems())
    }

    suspend fun getDeaths(): List<Death> {
        return dao.getAllDeaths().toItems()
    }

}