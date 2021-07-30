package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.framework.data.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.data.database.table.toItem
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import com.harismexis.breakingbad.model.domain.Actor
import com.harismexis.breakingbad.model.repository.ActorsLocal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActorsLocalRepository @Inject constructor(
    private val dao: BreakingBadLocalDao
): ActorsLocal {

    override suspend fun updateActors(items: List<Actor>) {
        dao.deleteAllActors()
        dao.insertActors(items.toLocalItems())
    }

    override suspend fun getActor(itemId: Int): Actor? {
        val localItem = dao.getActorById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getActors(): List<Actor> {
        return dao.getAllActors().toItems()
    }

}