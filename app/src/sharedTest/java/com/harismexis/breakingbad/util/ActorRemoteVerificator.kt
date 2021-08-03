package com.harismexis.breakingbad.util

import com.harismexis.breakingbad.framework.data.network.model.RemoteActor
import com.harismexis.breakingbad.core.domain.Actor
import org.junit.Assert

class ActorRemoteVerificator {

    fun verifyActorsAgainstRemoteActors(
        actual: List<Actor>,
        remoteActors: List<RemoteActor?>
    ) {
        remoteActors.forEachIndexed lit@{ _, remoteActor ->
            if (remoteActor == null) return@lit
            actual.forEachIndexed { _, actor ->
                remoteActor.id?.let {
                    if (it == actor.id) {
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
        Assert.assertEquals(remoteActor.id, actual.id)
        Assert.assertEquals(remoteActor.name, actual.name)
        Assert.assertEquals(remoteActor.birthday, actual.birthday)
        Assert.assertEquals(remoteActor.img, actual.img)
        Assert.assertEquals(remoteActor.status, actual.status)
        Assert.assertEquals(remoteActor.nickname, actual.nickname)
        Assert.assertEquals(remoteActor.portrayed, actual.portrayed)
        Assert.assertEquals(remoteActor.category, actual.category)
    }

}

