package com.example.breakingbad.framework.datasource.network.data

import com.example.breakingbad.data.BreakingBadBaseRemoteDataSource
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Death
import com.example.breakingbad.domain.Episode
import com.example.breakingbad.domain.Quote
import com.example.breakingbad.framework.extensions.actor.toItems
import com.example.breakingbad.framework.extensions.death.toItems
import com.example.breakingbad.framework.extensions.episode.toItems
import com.example.breakingbad.framework.extensions.quote.toItems
import javax.inject.Inject

class BreakingBadRemoteDataSource @Inject constructor(
    private val dao: BreakingBadRemoteDao
) : BreakingBadBaseRemoteDataSource {

    override suspend fun getActors(): List<Actor> {
        return dao.getActors().toItems()
    }

    override suspend fun getActorsByName(name: String?): List<Actor> {
        return dao.getActorsByName(name).toItems()
    }

    override suspend fun getQuotes(): List<Quote> {
        return dao.getQuotes().toItems()
    }

    override suspend fun getDeaths(): List<Death> {
        return dao.getDeaths().toItems()
    }

    override suspend fun getEpisodes(): List<Episode> {
        return dao.getEpisodes().toItems()
    }
}