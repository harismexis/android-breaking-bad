package com.example.breakingbad.framework.datasource.network.data

import com.example.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemoteItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreakingBadRemoteDao @Inject constructor(private val api: BreakingBadApi) {

    suspend fun getBreakingBadCharacters(): List<BBCharacterRemoteItem?>? {
        return api.getBreakingBadCharacters()
    }

}