package com.harismexis.breakingbad.presentation.screens.home.model

import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.datamodel.repo.ActorLocalRepo
import com.harismexis.breakingbad.datamodel.repo.ActorRemoteRepo
import javax.inject.Inject

data class HomeModel @Inject constructor (
    private val actorRemote: ActorRemoteRepo,
    private val actorLocal: ActorLocalRepo
) {
    suspend fun getRemoteActors(name: String? = null): List<Actor> = actorRemote.getActors(name)

    suspend fun updateActors(items: List<Actor>) {
        actorLocal.updateActors(items)
    }

    suspend fun getLocalActor(itemId: Int): Actor? {
        return actorLocal.getActor(itemId)
    }

    suspend fun getLocalActors(): List<Actor> {
        return actorLocal.getActors()
    }

}