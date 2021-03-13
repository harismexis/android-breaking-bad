package com.example.breakingbad.data

import com.example.breakingbad.domain.BBCharacter

interface BBCharacterBaseRemoteDataSource {

    suspend fun getItems(): List<BBCharacter>

    suspend fun getItemsByName(name: String?): List<BBCharacter>

}