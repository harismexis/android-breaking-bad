package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Death
import com.example.breakingbad.domain.Quote

class BreakingBadLocalRepository(
    private val dataSource: BreakingBadBaseLocalDataSource
) {
    suspend fun insertActors(items: List<Actor>) = dataSource.insertActors(items)

    suspend fun getActor(itemId: Int): Actor? = dataSource.getActor(itemId)

    suspend fun getActors(): List<Actor> = dataSource.getActors()


    suspend fun getQuotes(): List<Quote> = dataSource.getQuotes()

    suspend fun insertQuotes(items: List<Quote>) = dataSource.insertQuotes(items)


    suspend fun getDeaths(): List<Death> = dataSource.getDeaths()

    suspend fun insertDeaths(items: List<Death>) = dataSource.insertDeaths(items)
}