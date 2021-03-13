package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Quote

data class BreakingBadRemoteRepository(
    private val dataSource: BreakingBadBaseRemoteDataSource
) {
    suspend fun getActors(): List<Actor> = dataSource.getActors()

    suspend fun getActorsByName(name: String?): List<Actor> = dataSource.getActorsByName(name)

    suspend fun getQuotes(): List<Quote> = dataSource.getQuotes()
}