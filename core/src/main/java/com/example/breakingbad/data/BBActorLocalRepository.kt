package com.example.breakingbad.data

import com.example.breakingbad.domain.BBActor

class BBActorLocalRepository(
    private val dataSource: BBActorBaseLocalDataSource
) {
    suspend fun insertItems(items: List<BBActor>) = dataSource.insert(items)

    suspend fun getItem(itemId: Int): BBActor? = dataSource.getItem(itemId)

    suspend fun getItems(): List<BBActor> = dataSource.getAll()
}