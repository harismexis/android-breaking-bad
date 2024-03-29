package com.harismexis.breakingbad.tests.extensions

import com.harismexis.breakingbad.base.BaseUnitTest
import com.harismexis.breakingbad.framework.data.database.table.toItems
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import com.harismexis.breakingbad.util.ActorLocalVerificator
import com.harismexis.breakingbad.util.verifyListSizeWhenAllIdsValid
import com.harismexis.breakingbad.util.verifyListsHaveSameSize
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LocalActorExtTest : BaseUnitTest() {

    private val verificator = ActorLocalVerificator()

    @Test
    fun itemsAreConvertedToLocalItems_then_localItemsListIsTheExpected() {
        // given
        val items = mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid()

        // when
        val localItems = items.toLocalItems()

        // then
        verifyListsHaveSameSize(items, localItems)
        verifyListSizeWhenAllIdsValid(items)
        verifyListSizeWhenAllIdsValid(localItems)
        verificator.verifyLocalActorsAgainstActors(localItems, items)
    }

    @Test
    fun localItemsAreConvertedToItems_then_itemListIsTheExpected() {
        // given
        val localItems = mockActorsProvider.getMockLocalActorsWhenJsonHasAllItemsValid()

        // when
        val items = localItems.toItems()

        // then
        verifyListsHaveSameSize(items, localItems)
        verifyListSizeWhenAllIdsValid(localItems)
        verifyListSizeWhenAllIdsValid(items)
        verificator.verifyActorsAgainstLocalActors(items, localItems)
    }

}
