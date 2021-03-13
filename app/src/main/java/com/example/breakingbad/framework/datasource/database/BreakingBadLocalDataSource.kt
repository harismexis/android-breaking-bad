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

    override suspend fun insert(items: List<Actor>) {
        dao.insertItems(items.toLocalItems())
    }

    override suspend fun getItem(itemId: Int): Actor? {
        val localItem = dao.getItemById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getAll(): List<Actor> {
        return dao.getAllItems().toItems()
    }

}