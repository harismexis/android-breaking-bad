package com.example.breakingbad.framework.datasource.network.api

import com.example.breakingbad.framework.datasource.network.model.RemoteActor
import com.example.breakingbad.framework.datasource.network.model.RemoteDeath
import com.example.breakingbad.framework.datasource.network.model.RemoteQuote
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("characters")
    suspend fun getCharacters(): List<RemoteActor?>?

    @GET("characters")
    suspend fun getCharactersByName(
        @Query("name") name: String?
    ): List<RemoteActor?>?

    @GET("quotes")
    suspend fun getQuotes(): List<RemoteQuote?>?

    @GET("deaths")
    suspend fun getDeaths(): List<RemoteDeath?>?

}