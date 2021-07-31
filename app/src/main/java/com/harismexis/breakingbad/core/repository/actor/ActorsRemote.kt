package com.harismexis.breakingbad.core.repository.actor

import com.harismexis.breakingbad.core.domain.Actor

interface ActorsRemote {

    suspend fun getActors(name: String? = null): List<Actor>

}