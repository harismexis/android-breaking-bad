package com.example.jsonfeed.data

import com.example.jsonfeed.domain.Item

data class RemoteRepository(
    private val dataSource: RemoteDataSource
) {
    suspend fun getItems(): List<Item>? = dataSource.getItems()
}