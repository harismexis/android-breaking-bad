package com.example.breakingbad.data

import com.example.breakingbad.domain.BBActor

data class BBActorRemoteRepository(
    private val dataSource: BBActorBaseRemoteDataSource
) {
    suspend fun getItems(): List<BBActor> = dataSource.getItems()

    suspend fun getItemsByName(name: String?): List<BBActor> = dataSource.getItemsByName(name)
}