package com.example.jsonfeed.framework.datasource.network

import retrofit2.http.GET

interface PokemonApi {

    @GET("cards")
    suspend fun getPokemonCards(): PokemonFeed?

}