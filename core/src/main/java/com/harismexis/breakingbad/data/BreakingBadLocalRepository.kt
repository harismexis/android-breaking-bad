package com.harismexis.breakingbad.data

import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.domain.Death
import com.harismexis.breakingbad.domain.Episode
import com.harismexis.breakingbad.domain.Quote

class BreakingBadLocalRepository(
    private val dataSource: BreakingBadBaseLocalDataSource
) {
    suspend fun updateActors(items: List<Actor>) = dataSource.updateActors(items)

    suspend fun getActor(itemId: Int): Actor? = dataSource.getActor(itemId)

    suspend fun getActors(): List<Actor> = dataSource.getActors()


    suspend fun getQuotes(): List<Quote> = dataSource.getQuotes()

    suspend fun getQuotesBySeries(seriesName: String?): List<Quote> = dataSource.getQuotesBySeries(seriesName)

    suspend fun insertQuotes(items: List<Quote>) = dataSource.insertQuotes(items)


    suspend fun getDeaths(): List<Death> = dataSource.getDeaths()

    suspend fun insertDeaths(items: List<Death>) = dataSource.insertDeaths(items)


    suspend fun getEpisodes(): List<Episode> = dataSource.getEpisodes()

    suspend fun insertEpisodes(items: List<Episode>) = dataSource.insertEpisodes(items)
}