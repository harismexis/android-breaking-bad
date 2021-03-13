package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor

class BreakingBadLocalRepository(
    private val dataSource: BreakingBadBaseLocalDataSource
) {
    suspend fun insertItems(items: List<Actor>) = dataSource.insert(items)

    suspend fun getItem(itemId: Int): Actor? = dataSource.getItem(itemId)

    suspend fun getItems(): List<Actor> = dataSource.getAll()
}