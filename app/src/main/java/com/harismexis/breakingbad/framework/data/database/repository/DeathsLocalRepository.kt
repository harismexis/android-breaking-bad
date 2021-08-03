package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.core.domain.Death
import com.harismexis.breakingbad.core.repository.death.DeathsLocal
import com.harismexis.breakingbad.framework.data.database.dao.DeathsDao
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeathsLocalRepository @Inject constructor(
    private val dao: DeathsDao
): DeathsLocal {

    override suspend fun save(items: List<Death>) {
        dao.delete()
        dao.insert(items.toLocalItems())
    }

    override suspend fun getDeaths(): List<Death> {
        return dao.getAll().toItems()
    }

}