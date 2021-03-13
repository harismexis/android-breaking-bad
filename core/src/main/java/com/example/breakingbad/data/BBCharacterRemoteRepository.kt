package com.example.breakingbad.data

import com.example.breakingbad.domain.BBCharacter

data class BBCharacterRemoteRepository(
    private val dataSource: BBCharacterBaseRemoteDataSource
) {
    suspend fun getItems(): List<BBCharacter> = dataSource.getItems()

    suspend fun getItemsByName(name: String?): List<BBCharacter> = dataSource.getItemsByName(name)
}