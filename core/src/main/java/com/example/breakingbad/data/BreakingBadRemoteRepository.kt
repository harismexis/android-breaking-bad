package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Death
import com.example.breakingbad.domain.Episode
import com.example.breakingbad.domain.Quote

data class BreakingBadRemoteRepository(
    private val dataSource: BreakingBadBaseRemoteDataSource
) {
    suspend fun getActors(name: String? = null): List<Actor> = dataSource.getActors(name)

    suspend fun getQuotes(): List<Quote> = dataSource.getQuotes()

    suspend fun getDeaths(): List<Death> = dataSource.getDeaths()

    suspend fun getEpisodes(): List<Episode> = dataSource.getEpisodes()
}