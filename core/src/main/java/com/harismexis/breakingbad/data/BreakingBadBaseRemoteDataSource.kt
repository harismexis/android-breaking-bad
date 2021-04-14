package com.harismexis.breakingbad.data

import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.domain.Death
import com.harismexis.breakingbad.domain.Episode
import com.harismexis.breakingbad.domain.Quote

interface BreakingBadBaseRemoteDataSource {

    suspend fun getActors(name: String? = null): List<Actor>

    suspend fun getQuotes(series: String? = null): List<Quote>

    suspend fun getDeaths(): List<Death>

    suspend fun getEpisodes(series: String? = null): List<Episode>

}