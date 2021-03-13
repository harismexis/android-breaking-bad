package com.example.breakingbad.extensions

import com.example.breakingbad.framework.extensions.actor.toItems
import com.example.breakingbad.framework.extensions.actor.toLocalItems
import com.example.breakingbad.setup.UnitTestSetup
import com.example.breakingbad.utils.BBCharacterItemLocalVerificator
import com.example.breakingbad.utils.verifyListSizeWhenAllIdsValid
import com.example.breakingbad.utils.verifyListsHaveSameSize
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LocalActorExtTest : UnitTestSetup() {

    private val verificator = BBCharacterItemLocalVerificator()

    @Test
    fun itemsAreConvertedToLocalItems_then_localItemsListIsTheExpected() {
        // given
        val items = mockParser.getMockBBCharsFromFeedWithAllItemsValid()

        // when
        val localItems = items.toLocalItems()

        // then
        verifyListsHaveSameSize(items, localItems)
        verifyListSizeWhenAllIdsValid(items)
        verifyListSizeWhenAllIdsValid(localItems)
        verificator.verifyLocalItemsAgainstBBCharacters(localItems, items)
    }

    @Test
    fun localItemsAreConvertedToItems_then_itemListIsTheExpected() {
        // given
        val localItems = mockParser.getMockBBCharsLocalFromFeedWithAllItemsValid()

        // when
        val items = localItems.toItems()

        // then
        verifyListsHaveSameSize(items, localItems)
        verifyListSizeWhenAllIdsValid(localItems)
        verifyListSizeWhenAllIdsValid(items)
        verificator.verifyBBCharactersAgainstLocalItems(items, localItems)
    }

    override fun initialiseClassUnderTest() {
        // do nothing
    }

}
