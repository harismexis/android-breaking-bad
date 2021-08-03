package com.harismexis.breakingbad.core.repository.actor

import com.harismexis.breakingbad.core.domain.Actor

interface ActorsLocal  {

    suspend fun save(items: List<Actor>)

    suspend fun getActor(itemId: Int): Actor?

    suspend fun getActors(): List<Actor>

    suspend fun searchActors(query: String?): List<Actor>

}