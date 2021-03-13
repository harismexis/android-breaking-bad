package com.example.breakingbad.framework.datasource.database

import com.example.breakingbad.data.BBActorBaseLocalDataSource
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.framework.extensions.toItem
import com.example.breakingbad.framework.extensions.toItems
import com.example.breakingbad.framework.extensions.toLocalItems
import javax.inject.Inject

class BBActorLocalDataSource @Inject constructor(
    private val dao: BBLocalDao
) : BBActorBaseLocalDataSource {

    override suspend fun insert(items: List<BBActor>) {
        dao.insertItems(items.toLocalItems())
    }

    override suspend fun getItem(itemId: Int): BBActor? {
        val localItem = dao.getItemById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getAll(): List<BBActor> {
        return dao.getAllItems().toItems()
    }

}