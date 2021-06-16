package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.domain.Actor

interface ActorsRemote {

    suspend fun getActors(name: String? = null): List<Actor>

}