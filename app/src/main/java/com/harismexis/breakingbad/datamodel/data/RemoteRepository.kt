//package com.harismexis.breakingbad.datamodel.data
//
//import com.harismexis.breakingbad.datamodel.domain.Actor
//import com.harismexis.breakingbad.datamodel.domain.Death
//import com.harismexis.breakingbad.datamodel.domain.Episode
//import com.harismexis.breakingbad.datamodel.domain.Quote
//import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
//import com.harismexis.breakingbad.framework.extensions.actor.toItems
//import com.harismexis.breakingbad.framework.extensions.death.toItems
//import com.harismexis.breakingbad.framework.extensions.episode.toItems
//import com.harismexis.breakingbad.framework.extensions.quote.toItems
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//data class RemoteRepository @Inject constructor(
//    private val dao: BreakingBadRemoteDao
//) {
//    suspend fun getActors(name: String? = null): List<Actor> = dao.getActors(name).toItems()
//
//    suspend fun getQuotes(series: String? = null): List<Quote> = dao.getQuotes(series).toItems()
//
//    suspend fun getDeaths(): List<Death> = dao.getDeaths().toItems()
//
//    suspend fun getEpisodes(series: String? = null): List<Episode> = dao.getEpisodes(series).toItems()
//}