package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor

interface BreakingBadBaseRemoteDataSource {

    suspend fun getItems(): List<Actor>

    suspend fun getItemsByName(name: String?): List<Actor>

}