package com.example.breakingbad.data

import com.example.breakingbad.domain.Actor

interface BreakingBadBaseLocalDataSource {

    suspend fun insertActors(items: List<Actor>)

    suspend fun getActor(itemId: Int): Actor?

    suspend fun getAllActors(): List<Actor>
}