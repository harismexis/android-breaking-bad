package com.harismexis.breakingbad.datamodel.repo

import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class ActorRemoteRepo @Inject constructor(
    private val dao: BreakingBadRemoteDao
) {
    suspend fun getActors(name: String? = null): List<Actor> = dao.getActors(name).toItems()

}