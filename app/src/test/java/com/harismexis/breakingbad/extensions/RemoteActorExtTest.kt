package com.harismexis.breakingbad.extensions

import com.harismexis.breakingbad.framework.extensions.actor.toItems
import com.harismexis.breakingbad.setup.UnitTestSetup
import com.harismexis.breakingbad.utils.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RemoteActorExtTest : UnitTestSetup() {

    private val verificator = ActorRemoteVerificator()

    @Test
    fun feedHasAllItemsValid_then_conversionToItemsIsCorrect() {
        // given
        val remoteItems = actorsParser.getMockRemoteActorsWhenJsonHasAllIdsValid()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListsHaveSameSize(remoteItems, items)
        verifyListSizeWhenAllIdsValid(remoteItems)
        verifyListSizeWhenAllIdsValid(items)
        verificator.verifyActorsAgainstRemoteActors(items, remoteItems)
    }

    @Test
    fun feedHasSomeIdsAbsent_then_conversionToItemsIsCorrect() {
        // given
        val remoteItems = actorsParser.getMockRemoteActorsWhenJsonHasSomeInvalidIds()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeWhenSomeIdsAbsent(items)
        verificator.verifyActorsAgainstRemoteActors(items, remoteItems)
    }

    @Test
    fun feedHasSomeEmptyItems_then_conversionToItemsIsCorrect() {
        // given
        val remoteItems = actorsParser.getMockRemoteActorsWhenJsonHasSomeEmptyItems()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeWhenSomeItemsEmpty(items)
        verificator.verifyActorsAgainstRemoteActors(items, remoteItems)
    }

    @Test
    fun feedHasAllIdsAbsent_then_itemListIsEmpty() {
        // given
        val remoteItems = actorsParser.getMockRemoteActorsWhenJsonHasAllIdsInvalid()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    @Test
    fun feedIsAnEmptyJson_then_itemListIsEmpty() {
        // given
        val remoteItems = actorsParser.getMockRemoteActorsWhenJsonIsEmpty()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    override fun initialiseClassUnderTest() {
        // Do nothing
    }

}
