package com.harismexis.breakingbad.datamodel.data

import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.datamodel.domain.Death
import com.harismexis.breakingbad.datamodel.domain.Episode
import com.harismexis.breakingbad.datamodel.domain.Quote
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.extensions.actor.toItem
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import com.harismexis.breakingbad.framework.extensions.actor.toLocalItems
import com.harismexis.breakingbad.framework.extensions.death.toItems
import com.harismexis.breakingbad.framework.extensions.death.toLocalItems
import com.harismexis.breakingbad.framework.extensions.episode.toItems
import com.harismexis.breakingbad.framework.extensions.episode.toLocalItems
import com.harismexis.breakingbad.framework.extensions.quote.toItems
import com.harismexis.breakingbad.framework.extensions.quote.toLocalItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val dao: BreakingBadLocalDao
) {

    suspend fun updateActors(items: List<Actor>) {
        dao.deleteAllActors()
        dao.insertActors(items.toLocalItems())
    }

    suspend fun getActor(itemId: Int): Actor? {
        val localItem = dao.getActorById(itemId)
        localItem?.let {
            return it.toItem()
        }
        return null
    }

    suspend fun getActors(): List<Actor> {
        return dao.getAllActors().toItems()
    }

    suspend fun insertQuotes(items: List<Quote>) {
        dao.insertQuotes(items.toLocalItems())
    }

    suspend fun getQuotes(): List<Quote> {
        return dao.getAllQuotes().toItems()
    }

    suspend fun getQuotesBySeries(seriesName: String?): List<Quote> {
        return dao.getQuotesBySeries(seriesName).toItems()
    }


    suspend fun insertDeaths(items: List<Death>) {
        dao.insertDeaths(items.toLocalItems())
    }

    suspend fun getDeaths(): List<Death> {
        return dao.getAllDeaths().toItems()
    }

    suspend fun insertEpisodes(items: List<Episode>) {
        dao.insertEpisodes(items.toLocalItems())
    }

    suspend fun getEpisodes(): List<Episode> {
        return dao.getAllEpisodes().toItems()
    }

}