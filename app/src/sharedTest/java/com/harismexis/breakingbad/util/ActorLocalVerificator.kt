package com.harismexis.breakingbad.util

import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.framework.data.database.table.LocalActor
import org.junit.Assert

class ActorLocalVerificator {

    fun verifyActorsAgainstLocalActors(
        actual: List<Actor>,
        localActors: List<LocalActor>
    ) {
        verifyListsHaveSameSize(actual, localActors)
        localActors.forEachIndexed { index, localActor ->
            val actor = actual[index]
            verifyActorAgainstLocalActor(actor, localActor)
        }
    }

    private fun verifyActorAgainstLocalActor(
        actual: Actor,
        localActor: LocalActor
    ) {
        Assert.assertEquals(localActor.id, actual.id)
        Assert.assertEquals(localActor.name, actual.name)
        Assert.assertEquals(localActor.birthday, actual.birthday)
        Assert.assertEquals(localActor.img, actual.img)
        Assert.assertEquals(localActor.status, actual.status)
        Assert.assertEquals(localActor.nickname, actual.nickname)
        Assert.assertEquals(localActor.portrayed, actual.portrayed)
        Assert.assertEquals(localActor.category, actual.category)
    }

    fun verifyLocalActorsAgainstActors(
        actual: List<LocalActor>,
        actors: List<Actor>
    ) {
        verifyListsHaveSameSize(actual, actors)
        actors.forEachIndexed { index, item ->
            val localItem = actual[index]
            verifyLocalActorAgainstActor(localItem, item)
        }
    }

    private fun verifyLocalActorAgainstActor(
        actual: LocalActor,
        actor: Actor
    ) {
        Assert.assertEquals(actor.id, actual.id)
        Assert.assertEquals(actor.name, actual.name)
        Assert.assertEquals(actor.birthday, actual.birthday)
        Assert.assertEquals(actor.img, actual.img)
        Assert.assertEquals(actor.status, actual.status)
        Assert.assertEquals(actor.nickname, actual.nickname)
        Assert.assertEquals(actor.portrayed, actual.portrayed)
        Assert.assertEquals(actor.category, actual.category)
    }

}

