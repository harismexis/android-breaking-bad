package com.example.breakingbad.framework.datasource.database

import com.example.breakingbad.data.BreakingBadBaseLocalDataSource
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.extensions.toItem
import com.example.breakingbad.framework.extensions.toItems
import com.example.breakingbad.framework.extensions.toLocalItems
import javax.inject.Inject

class BreakingBadLocalDataSource @Inject constructor(
    private val dao: BreakingBadLocalDao
) : BreakingBadBaseLocalDataSource {

    override suspend fun insertActors(items: List<Actor>) {
        dao.insertActors(items.toLocalItems())
    }

    override suspend fun getActor(itemId: Int): Actor? {
        val localItem = dao.getActorById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getAllActors(): List<Actor> {
        return dao.getAllActors().toItems()
    }

}