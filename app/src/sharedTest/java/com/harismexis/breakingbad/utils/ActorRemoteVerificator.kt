package com.harismexis.breakingbad.utils

import com.harismexis.breakingbad.model.domain.Actor
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteActor
import org.junit.Assert

class ActorRemoteVerificator {

    fun verifyActorsAgainstRemoteActors(
        actual: List<Actor>,
        remoteActors: List<RemoteActor?>
    ) {
        remoteActors.forEachIndexed lit@{ _, remoteActor ->
            if (remoteActor == null) return@lit
            actual.forEachIndexed { _, actor ->
                remoteActor.actorId?.let {
                    if (it == actor.actorId) {
                        verifyActorAgainstRemoteActor(actor, remoteActor)
                        return@lit
                    }
                }
            }
        }
    }

    private fun verifyActorAgainstRemoteActor(
        actual: Actor,
        remoteActor: RemoteActor
    ) {
        Assert.assertEquals(remoteActor.actorId, actual.actorId)
        Assert.assertEquals(remoteActor.name, actual.name)
        Assert.assertEquals(remoteActor.birthday, actual.birthday)
        Assert.assertEquals(remoteActor.img, actual.img)
        Assert.assertEquals(remoteActor.status, actual.status)
        Assert.assertEquals(remoteActor.nickname, actual.nickname)
        Assert.assertEquals(remoteActor.portrayed, actual.portrayed)
        Assert.assertEquals(remoteActor.category, actual.category)
    }

}

