package com.example.breakingbad.framework.datasource.network.data

import com.example.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.example.breakingbad.framework.datasource.network.model.RemoteActor
import com.example.breakingbad.framework.datasource.network.model.RemoteQuote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingBadRemoteDao @Inject constructor(private val api: BreakingBadApi) {

    suspend fun getActors(): List<RemoteActor?>? {
        return api.getBreakingBadCharacters()
    }

    suspend fun getActorsByName(name: String?): List<RemoteActor?>? {
        return api.getBreakingBadCharactersByName(name)
    }

    suspend fun getQuotes(): List<RemoteQuote?>? {
        return api.getBreakingBadQuotes()
    }

}