package com.harismexis.breakingbad.data

import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.domain.Death
import com.harismexis.breakingbad.domain.Episode
import com.harismexis.breakingbad.domain.Quote

data class BreakingBadRemoteRepository(
    private val dataSource: BreakingBadBaseRemoteDataSource
) {
    suspend fun getActors(name: String? = null): List<Actor> = dataSource.getActors(name)

    suspend fun getQuotes(): List<Quote> = dataSource.getQuotes()

    suspend fun getDeaths(): List<Death> = dataSource.getDeaths()

    suspend fun getEpisodes(series: String? = null): List<Episode> = dataSource.getEpisodes(series)
}