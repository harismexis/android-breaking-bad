package com.example.breakingbad.framework.datasource.network.data

import com.example.breakingbad.data.BBCharacterBaseRemoteDataSource
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.framework.extensions.toItems
import javax.inject.Inject

class BBCharacterRemoteDataSource @Inject constructor(
    private val dao: BreakingBadRemoteDao
) : BBCharacterBaseRemoteDataSource {

    override suspend fun getItems(): List<BBCharacter> {
        return dao.getBreakingBadCharacters().toItems()
    }

}