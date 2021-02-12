package com.example.jsonfeed.data

import com.example.jsonfeed.domain.Item

class LocalRepository(
    private val dataSource: LocalDataSource
) {
    suspend fun insertItems(items: List<Item>) = dataSource.insert(items)

    suspend fun getItem(itemId: String): Item? = dataSource.getItem(itemId)

    suspend fun getItems(): List<Item>? = dataSource.getAll()
}