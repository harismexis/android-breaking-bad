package com.harismexis.breakingbad.datamodel.repo

import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.extensions.actor.toItem
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import com.harismexis.breakingbad.framework.extensions.actor.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActorLocalRepo @Inject constructor(
    private val dao: BreakingBadLocalDao
) {
    suspend fun updateActors(items: List<Actor>) {
        dao.deleteAllActors()
        dao.insertActors(items.toLocalItems())
    }

    suspend fun getActor(itemId: Int): Actor? {
        val localItem = dao.getActorById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    suspend fun getActors(): List<Actor> {
        return dao.getAllActors().toItems()
    }

}