package com.harismexis.breakingbad.framework.data.network.repository

import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.core.repository.actor.ActorsRemote
import com.harismexis.breakingbad.framework.data.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.data.network.model.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class ActorsRemoteRepository @Inject constructor(
    private val api: BreakingBadApi
): ActorsRemote {

    override suspend fun getActors(name: String?): List<Actor> {
        return api.getActors(name).toItems()
    }

}