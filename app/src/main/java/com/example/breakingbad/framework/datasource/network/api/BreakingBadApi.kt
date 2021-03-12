package com.example.breakingbad.framework.datasource.network.api

import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemote
import retrofit2.http.GET

interface BreakingBadApi {

    @GET("characters")
    suspend fun getBreakingBadCharacters(): List<BBCharacterRemote?>?

}