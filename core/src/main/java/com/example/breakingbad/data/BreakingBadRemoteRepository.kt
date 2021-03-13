package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor

data class BreakingBadRemoteRepository(
    private val dataSource: BreakingBadBaseRemoteDataSource
) {
    suspend fun getItems(): List<Actor> = dataSource.getItems()

    suspend fun getItemsByName(name: String?): List<Actor> = dataSource.getItemsByName(name)
}