package com.example.breakingbad.framework.datasource.network.data

import com.example.breakingbad.data.BBActorBaseRemoteDataSource
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.framework.extensions.toItems
import javax.inject.Inject

class BBActorRemoteDataSource @Inject constructor(
    private val dao: BreakingBadRemoteDao
) : BBActorBaseRemoteDataSource {

    override suspend fun getItems(): List<BBActor> {
        return dao.getBreakingBadCharacters().toItems()
    }

    override suspend fun getItemsByName(name: String?): List<BBActor> {
        return dao.getBreakingBadCharactersByName(name).toItems()
    }

}