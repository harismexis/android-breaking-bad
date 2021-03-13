package com.example.breakingbad.framework.datasource.network.data

import com.example.breakingbad.data.BreakingBadBaseRemoteDataSource
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.extensions.toItems
import javax.inject.Inject

class BreakingBadRemoteDataSource @Inject constructor(
    private val dao: BreakingBadRemoteDao
) : BreakingBadBaseRemoteDataSource {

    override suspend fun getItems(): List<Actor> {
        return dao.getBreakingBadCharacters().toItems()
    }

    override suspend fun getItemsByName(name: String?): List<Actor> {
        return dao.getBreakingBadCharactersByName(name).toItems()
    }

}