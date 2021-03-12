package com.example.breakingbad.framework.datasource.network.api

import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemoteItem
import retrofit2.http.GET

interface BreakingBadApi {

    @GET("breeds")
    suspend fun getBreakingBadCharacters(): List<BBCharacterRemoteItem?>?

}