package com.harismexis.breakingbad.util

import com.harismexis.breakingbad.mocks.MockActorsProvider
import org.junit.Assert

fun <T, P> verifyListsHaveSameSize(
    list0: List<T>,
    list1: List<P>
) {
    Assert.assertEquals(list0.size, list1.size)
}

fun <T> verifyListSize(
    expectedSize: Int,
    items: List<T>
) {
    Assert.assertEquals(expectedSize, items.size)
}

fun <T> verifyListSizeWhenAllIdsValid(items: List<T>) {
    verifyListSize(MockActorsProvider.NUM_ACTORS_WHEN_ALL_IDS_VALID, items)
}

fun <T> verifyListSizeWhenSomeIdsAbsent(items: List<T>) {
    verifyListSize(MockActorsProvider.NUM_ACTORS_WHEN_SOME_IDS_INVALID, items)
}

fun <T> verifyListSizeWhenSomeItemsEmpty(items: List<T>) {
    verifyListSize(MockActorsProvider.NUM_ACTORS_WHEN_SOME_EMPTY, items)
}

fun <T> verifyListSizeForNoData(items: List<T>) {
    verifyListSize(MockActorsProvider.NUM_ACTORS_WHEN_NO_DATA, items)
}