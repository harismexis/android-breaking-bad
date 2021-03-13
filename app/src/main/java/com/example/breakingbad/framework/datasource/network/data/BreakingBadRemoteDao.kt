package com.example.breakingbad.framework.datasource.network.data

import com.example.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingBadRemoteDao @Inject constructor(private val api: BreakingBadApi) {

    suspend fun getBreakingBadCharacters(): List<BBCharacterRemote?>? {
        return api.getBreakingBadCharacters()
    }

    suspend fun getBreakingBadCharactersByName(name: String?): List<BBCharacterRemote?>? {
        return api.getBreakingBadCharactersByName(name)
    }

}