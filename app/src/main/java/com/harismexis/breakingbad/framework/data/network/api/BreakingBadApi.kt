package com.harismexis.breakingbad.framework.data.network.api

import com.harismexis.breakingbad.framework.data.network.model.RemoteActor
import com.harismexis.breakingbad.framework.data.network.model.RemoteDeath
import com.harismexis.breakingbad.framework.data.network.model.RemoteEpisode
import com.harismexis.breakingbad.framework.data.network.model.RemoteQuote
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("characters")
    suspend fun getCharactersByName(
        @Query("name") name: String? = null
    ): List<RemoteActor?>?

    @GET("quotes")
    suspend fun getQuotes(
        @Query("series") series: String? = null
    ): List<RemoteQuote?>?

    @GET("deaths")
    suspend fun getDeaths(): List<RemoteDeath?>?

    @GET("episodes")
    suspend fun getEpisodes(
        @Query("series") series: String? = null
    ): List<RemoteEpisode?>?

}