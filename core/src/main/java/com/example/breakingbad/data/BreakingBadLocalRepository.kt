package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor

class BreakingBadLocalRepository(
    private val dataSource: BreakingBadBaseLocalDataSource
) {
    suspend fun insertActors(items: List<Actor>) = dataSource.insertActors(items)

    suspend fun getActor(itemId: Int): Actor? = dataSource.getActor(itemId)

    suspend fun getActors(): List<Actor> = dataSource.getAllActors()
}