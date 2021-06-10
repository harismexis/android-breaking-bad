package com.harismexis.breakingbad.model.repository

import com.harismexis.breakingbad.model.domain.Actor
import com.harismexis.breakingbad.model.datasource.network.dao.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class ActorsRemoteRepository @Inject constructor(
    private val dao: BreakingBadRemoteDao
) {
    suspend fun getActors(name: String? = null): List<Actor> = dao.getActors(name).toItems()

}