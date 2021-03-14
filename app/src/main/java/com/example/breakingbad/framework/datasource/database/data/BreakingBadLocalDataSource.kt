package com.example.breakingbad.framework.datasource.database.data

import com.example.breakingbad.data.BreakingBadBaseLocalDataSource
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Death
import com.example.breakingbad.domain.Episode
import com.example.breakingbad.domain.Quote
import com.example.breakingbad.framework.extensions.actor.toItem
import com.example.breakingbad.framework.extensions.actor.toItems
import com.example.breakingbad.framework.extensions.actor.toLocalItems
import com.example.breakingbad.framework.extensions.death.toItems
import com.example.breakingbad.framework.extensions.death.toLocalItems
import com.example.breakingbad.framework.extensions.episode.toItems
import com.example.breakingbad.framework.extensions.episode.toLocalItems
import com.example.breakingbad.framework.extensions.quote.toItems
import com.example.breakingbad.framework.extensions.quote.toLocalItems
import javax.inject.Inject

class BreakingBadLocalDataSource @Inject constructor(
    private val dao: BreakingBadLocalDao
) : BreakingBadBaseLocalDataSource {

    override suspend fun insertActors(items: List<Actor>) {
        dao.insertActors(items.toLocalItems())
    }

    override suspend fun getActor(itemId: Int): Actor? {
        val localItem = dao.getActorById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    override suspend fun getActors(): List<Actor> {
        return dao.getAllActors().toItems()
    }

    override suspend fun insertQuotes(items: List<Quote>) {
        dao.insertQuotes(items.toLocalItems())
    }

    override suspend fun getQuotes(): List<Quote> {
        return dao.getAllQuotes().toItems()
    }

    override suspend fun insertDeaths(items: List<Death>) {
        dao.insertDeaths(items.toLocalItems())
    }

    override suspend fun getDeaths(): List<Death> {
        return dao.getAllDeaths().toItems()
    }

    override suspend fun insertEpisodes(items: List<Episode>) {
        dao.insertEpisodes(items.toLocalItems())
    }

    override suspend fun getEpisodes(): List<Episode> {
        return dao.getAllEpisodes().toItems()
    }

}