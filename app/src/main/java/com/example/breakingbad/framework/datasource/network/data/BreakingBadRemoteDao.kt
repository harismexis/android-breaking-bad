package com.example.breakingbad.framework.datasource.network.data

import com.example.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.example.breakingbad.framework.datasource.network.model.RemoteActor
import com.example.breakingbad.framework.datasource.network.model.RemoteDeath
import com.example.breakingbad.framework.datasource.network.model.RemoteEpisode
import com.example.breakingbad.framework.datasource.network.model.RemoteQuote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingBadRemoteDao @Inject constructor(private val api: BreakingBadApi) {

    suspend fun getActors(name: String? = null): List<RemoteActor?>? {
        return api.getCharactersByName(name)
    }

    suspend fun getQuotes(): List<RemoteQuote?>? {
        return api.getQuotes()
    }

    suspend fun getDeaths(): List<RemoteDeath?>? {
        return api.getDeaths()
    }

    suspend fun getEpisodes(): List<RemoteEpisode?>? {
        return api.getEpisodes()
    }

}