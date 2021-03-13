package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor

interface BreakingBadBaseRemoteDataSource {

    suspend fun getActors(): List<Actor>

    suspend fun getActorsByName(name: String?): List<Actor>

}