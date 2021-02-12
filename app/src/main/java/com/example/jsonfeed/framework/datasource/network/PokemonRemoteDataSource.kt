package com.example.jsonfeed.framework.datasource.network

import com.example.jsonfeed.data.RemoteDataSource
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.extensions.toItems

import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val dao: PokemonRemoteDao
) : RemoteDataSource {

    override suspend fun getItems(): List<Item>? {
        return dao.getPokemonCards().toItems()
    }

}