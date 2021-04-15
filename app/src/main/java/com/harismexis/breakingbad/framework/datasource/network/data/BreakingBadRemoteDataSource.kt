package com.harismexis.breakingbad.framework.datasource.network.data

import com.harismexis.breakingbad.data.BreakingBadBaseRemoteDataSource
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.domain.Death
import com.harismexis.breakingbad.domain.Episode
import com.harismexis.breakingbad.domain.Quote
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import com.harismexis.breakingbad.framework.extensions.death.toItems
import com.harismexis.breakingbad.framework.extensions.episode.toItems
import com.harismexis.breakingbad.framework.extensions.quote.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingBadRemoteDataSource @Inject constructor(
    private val dao: BreakingBadRemoteDao
) : BreakingBadBaseRemoteDataSource {

    override suspend fun getActors(name: String?): List<Actor> {
        return dao.getActors(name).toItems()
    }

    override suspend fun getQuotes(series: String?): List<Quote> {
        return dao.getQuotes(series).toItems()
    }

    override suspend fun getDeaths(): List<Death> {
        return dao.getDeaths().toItems()
    }

    override suspend fun getEpisodes(series: String?): List<Episode> {
        return dao.getEpisodes(series).toItems()
    }
}