package com.harismexis.breakingbad.model.repository.actor

import com.harismexis.breakingbad.model.domain.Actor

interface ActorsRemote {

    suspend fun getActors(name: String? = null): List<Actor>

}