package com.harismexis.breakingbad.utils

import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor
import org.junit.Assert

class ActorLocalVerificator {

    fun verifyActorsAgainstLocalActors(
        actual: List<Actor>,
        expected: List<LocalActor>
    ) {
        verifyListsHaveSameSize(actual, expected)
        expected.forEachIndexed { index, localItem ->
            val item = actual[index]
            verifyActorAgainstLocalActor(item, localItem)
        }
    }

    private fun verifyActorAgainstLocalActor(
        actual: Actor,
        expected: LocalActor
    ) {
        Assert.assertEquals(expected.actorId, actual.actorId)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.birthday, actual.birthday)
        Assert.assertEquals(expected.img, actual.img)
        Assert.assertEquals(expected.status, actual.status)
        Assert.assertEquals(expected.nickname, actual.nickname)
        Assert.assertEquals(expected.portrayed, actual.portrayed)
        Assert.assertEquals(expected.category, actual.category)
    }

    fun verifyLocalActorsAgainstActors(
        actual: List<LocalActor>,
        expected: List<Actor>
    ) {
        verifyListsHaveSameSize(actual, expected)
        expected.forEachIndexed { index, item ->
            val localItem = actual[index]
            verifyLocalActorAgainstActor(localItem, item)
        }
    }

    private fun verifyLocalActorAgainstActor(
        actual: LocalActor,
        expected: Actor
    ) {
        Assert.assertEquals(expected.actorId, actual.actorId)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.birthday, actual.birthday)
        Assert.assertEquals(expected.img, actual.img)
        Assert.assertEquals(expected.status, actual.status)
        Assert.assertEquals(expected.nickname, actual.nickname)
        Assert.assertEquals(expected.portrayed, actual.portrayed)
        Assert.assertEquals(expected.category, actual.category)
    }

}

