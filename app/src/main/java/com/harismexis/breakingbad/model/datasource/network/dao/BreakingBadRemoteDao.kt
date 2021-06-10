package com.harismexis.breakingbad.model.datasource.network.dao

import com.harismexis.breakingbad.model.datasource.network.api.BreakingBadApi
import com.harismexis.breakingbad.model.datasource.network.model.RemoteActor
import com.harismexis.breakingbad.model.datasource.network.model.RemoteDeath
import com.harismexis.breakingbad.model.datasource.network.model.RemoteEpisode
import com.harismexis.breakingbad.model.datasource.network.model.RemoteQuote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingBadRemoteDao @Inject constructor(private val api: BreakingBadApi) {

    suspend fun getActors(name: String? = null): List<RemoteActor?>? {
        return api.getCharactersByName(name)
    }

    suspend fun getQuotes(series: String? = null): List<RemoteQuote?>? {
        return api.getQuotes(series)
    }

    suspend fun getDeaths(): List<RemoteDeath?>? {
        return api.getDeaths()
    }

    suspend fun getEpisodes(series: String? = null): List<RemoteEpisode?>? {
        return api.getEpisodes(series)
    }

}