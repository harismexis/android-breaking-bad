package com.example.breakingbad.framework.datasource.network.api

import com.example.breakingbad.framework.datasource.network.model.RemoteActor
import com.example.breakingbad.framework.datasource.network.model.RemoteDeath
import com.example.breakingbad.framework.datasource.network.model.RemoteEpisode
import com.example.breakingbad.framework.datasource.network.model.RemoteQuote
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("characters")
    suspend fun getCharactersByName(
        @Query("name") name: String? = null
    ): List<RemoteActor?>?

    @GET("quotes")
    suspend fun getQuotes(): List<RemoteQuote?>?

    @GET("deaths")
    suspend fun getDeaths(): List<RemoteDeath?>?

    @GET("episodes")
    suspend fun getEpisodes(): List<RemoteEpisode?>?

}