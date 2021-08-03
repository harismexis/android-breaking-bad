package com.harismexis.breakingbad.framework.data.database.repository

import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.core.repository.actor.ActorsLocal
import com.harismexis.breakingbad.framework.data.database.dao.ActorsDao
import com.harismexis.breakingbad.framework.data.database.table.toItem
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActorsLocalRepository @Inject constructor(
    private val dao: ActorsDao
): ActorsLocal {

    override suspend fun save(items: List<Actor>) {
        dao.delete()
        dao.insert(items.toLocalItems())
    }

    override suspend fun getActor(itemId: Int): Actor? {
        val localItem = dao.getActorById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getActors(): List<Actor> {
        return dao.getAll().toItems()
    }

    override suspend fun searchActors(query: String?): List<Actor> {
        return dao.searchActors(query).toItems()
    }

}