package com.example.breakingbad.framework.datasource.database

import com.example.breakingbad.data.BBCharacterBaseLocalDataSource
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.framework.extensions.toItem
import com.example.breakingbad.framework.extensions.toItems
import com.example.breakingbad.framework.extensions.toLocalItems
import javax.inject.Inject

class BBCharacterLocalDataSource @Inject constructor(
    private val dao: BBLocalDao
) : BBCharacterBaseLocalDataSource {

    override suspend fun insert(items: List<BBCharacter>) {
        dao.insertItems(items.toLocalItems())
    }

    override suspend fun getItem(itemId: String): BBCharacter? {
        val localItem = dao.getItemById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getAll(): List<BBCharacter> {
        return dao.getAllItems().toItems()
    }

}