package com.example.breakingbad.data

import com.example.breakingbad.domain.BBCharacter

class BBCharacterLocalRepository(
    private val dataSource: BBCharacterBaseLocalDataSource
) {
    suspend fun insertItems(items: List<BBCharacter>) = dataSource.insert(items)

    suspend fun getItem(itemId: String): BBCharacter? = dataSource.getItem(itemId)

    suspend fun getItems(): List<BBCharacter> = dataSource.getAll()
}