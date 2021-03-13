package com.example.breakingbad.framework.datasource.network.api

import com.example.breakingbad.framework.datasource.network.model.RemoteActor
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("characters")
    suspend fun getBreakingBadCharacters(): List<RemoteActor?>?

    @GET("characters")
    suspend fun getBreakingBadCharactersByName(
        @Query("name") name: String?
    ): List<RemoteActor?>?

}