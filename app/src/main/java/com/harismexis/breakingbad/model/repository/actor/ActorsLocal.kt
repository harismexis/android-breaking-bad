package com.harismexis.breakingbad.model.repository.actor

import com.harismexis.breakingbad.model.domain.Actor

interface ActorsLocal  {

    suspend fun updateActors(items: List<Actor>)

    suspend fun getActor(itemId: Int): Actor?

    suspend fun getActors(): List<Actor>

}