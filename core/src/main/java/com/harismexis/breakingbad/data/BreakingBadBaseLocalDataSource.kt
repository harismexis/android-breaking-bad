package com.harismexis.breakingbad.data

import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.domain.Death
import com.harismexis.breakingbad.domain.Episode
import com.harismexis.breakingbad.domain.Quote

interface BreakingBadBaseLocalDataSource {

    suspend fun updateActors(items: List<Actor>)

    suspend fun getActor(itemId: Int): Actor?

    suspend fun getActors(): List<Actor>


    suspend fun getQuotes(): List<Quote>

    suspend fun getQuotesBySeries(seriesName: String?): List<Quote>

    suspend fun insertQuotes(items: List<Quote>)


    suspend fun getDeaths(): List<Death>

    suspend fun insertDeaths(items: List<Death>)


    suspend fun getEpisodes(): List<Episode>

    suspend fun insertEpisodes(items: List<Episode>)

}