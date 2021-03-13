package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor

data class BreakingBadRemoteRepository(
    private val dataSource: BreakingBadBaseRemoteDataSource
) {
    suspend fun getActors(): List<Actor> = dataSource.getActors()

    suspend fun getActorsByName(name: String?): List<Actor> = dataSource.getActorsByName(name)
}