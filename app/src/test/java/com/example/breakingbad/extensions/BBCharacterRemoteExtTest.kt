package com.example.breakingbad.extensions

import com.example.breakingbad.framework.extensions.toItems
import com.example.breakingbad.utils.*
import com.example.breakingbad.setup.UnitTestSetup
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BBCharacterRemoteExtTest : UnitTestSetup() {

    private val verificator = BBCharacterItemRemoteVerificator()

    @Test
    fun feedHasAllItemsValid_then_conversionToItemsIsCorrect() {
        // given
        val remoteItems = mockParser.getMockBBCharsFeedAllIdsValid()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListsHaveSameSize(remoteItems, items)
        verifyListSizeWhenAllIdsValid(remoteItems)
        verifyListSizeWhenAllIdsValid(items)
        verificator.verifyBBCharactersAgainstRemoteItems(items, remoteItems)
    }

    @Test
    fun feedHasSomeIdsAbsent_then_conversionToItemsIsCorrect() {
        // given
        val remoteItems = mockParser.getMockBBCharsFeedSomeIdsAbsent()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeWhenSomeIdsAbsent(items)
        verificator.verifyBBCharactersAgainstRemoteItems(items, remoteItems)
    }

    @Test
    fun feedHasSomeEmptyItems_then_conversionToItemsIsCorrect() {
        // given
        val remoteItems = mockParser.getMockBBCharsFeedSomeItemsEmpty()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeWhenSomeItemsEmpty(items)
        verificator.verifyBBCharactersAgainstRemoteItems(items, remoteItems)
    }

    @Test
    fun feedHasAllIdsAbsent_then_itemListIsEmpty() {
        // given
        val remoteItems = mockParser.getMockBBCharsFeedAllIdsAbsent()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    @Test
    fun feedIsAnEmptyJson_then_itemListIsEmpty() {
        // given
        val remoteItems = mockParser.getMockBBCharsFeedEmptyJsonArray()

        // when
        val items = remoteItems.toItems()

        // then
        verifyListSizeForNoData(items)
    }

    override fun initialiseClassUnderTest() {
        // Do nothing
    }

}
