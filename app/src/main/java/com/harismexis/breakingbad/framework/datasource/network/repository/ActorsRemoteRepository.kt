package com.harismexis.breakingbad.framework.datasource.network.repository

import com.harismexis.breakingbad.framework.datasource.network.dao.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import com.harismexis.breakingbad.model.domain.Actor
import com.harismexis.breakingbad.model.repository.ActorsRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class ActorsRemoteRepository @Inject constructor(
    private val dao: BreakingBadRemoteDao
): ActorsRemote {
    override suspend fun getActors(name: String?): List<Actor> = dao.getActors(name).toItems()

}