package com.harismexis.breakingbad.tests.extensions

import com.harismexis.breakingbad.base.BaseUnitTest
import com.harismexis.breakingbad.framework.data.network.model.toItems
import com.harismexis.breakingbad.util.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RemoteActorExtTest : BaseUnitTest() {

    private val verificator = ActorRemoteVerificator()

    @Test
    fun feedHasAllItemsValid_then_conversionToItemsIsCorrect() {
        // given
        val remoteItems = mockActorsProvider.getMockRemoteActorsWhenJsonHasAllIdsValid()

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
        val remoteItems = mockActorsProvider.getMockRemoteActorsWhenJsonHasSomeInvalidIds()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeWhenSomeIdsAbsent(items)
        verificator.verifyActorsAgainstRemoteActors(items, remoteItems)
    }

    @Test
    fun feedHasSomeEmptyItems_then_conversionToItemsIsCorrect() {
        // given
        val remoteItems = mockActorsProvider.getMockRemoteActorsWhenJsonHasSomeEmptyItems()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeWhenSomeItemsEmpty(items)
        verificator.verifyActorsAgainstRemoteActors(items, remoteItems)
    }

    @Test
    fun feedHasAllIdsAbsent_then_itemListIsEmpty() {
        // given
        val remoteItems = mockActorsProvider.getMockRemoteActorsWhenJsonHasAllIdsInvalid()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    @Test
    fun feedIsAnEmptyJson_then_itemListIsEmpty() {
        // given
        val remoteItems = mockActorsProvider.getMockRemoteActorsWhenJsonIsEmpty()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeForNoData(items)
    }

}
