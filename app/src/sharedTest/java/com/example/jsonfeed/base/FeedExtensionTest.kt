package com.example.jsonfeed.base//package com.example.jsonfeed.framework.extensions
//
//import com.example.jsonfeed.framework.datasource.network.Feed
//
//import org.junit.Test
//
//class FeedExtensionTest : BaseTestSetup() {
//
//    @Test
//    fun feedContainsValidItems_conversionToLocalItemsIsCorrect() {
//        // given
//        val feed = getMockFeedAllIdsValid()
//
//        // when
//        val localItems = feed.toLocalItems()
//
//        // then
//        verifyListsHaveSameSize(feed.cards!!, localItems)
//        verifyListSizeWhenAllIdsValid(feed.cards!!)
//        verifyListSizeWhenAllIdsValid(localItems)
//
//        feed.cards!!.forEachIndexed { index, feedItem ->
//            val localItem = localItems[index]
//            verifyLocalItemAgainstFeedItem(feedItem!!, localItem)
//        }
//    }
//
//    @Test
//    fun feedHasSomeIdsAbsent_conversionToLocalItemsIsCorrect() {
//        // given
//        val feed = getMockFeedSomeIdsAbsent()
//
//        // when
//        val localItems = feed.toLocalItems()
//
//        // then
//        verifyListSizeWhenSomeIdsAbsent(localItems)
//        verifyLocalItemsAgainstFeedItems(feed, localItems)
//    }
//
//    @Test
//    fun feedHasAllIdsAbsent_localItemsAreEmpty() {
//        // given
//        val feed = getMockFeedAllIdsAbsent()
//
//        // when
//        val localItems = feed.toLocalItems()
//
//        // then
//        verifyListSizeWhenAllIdsAbsent(localItems)
//    }
//
//    @Test
//    fun feedHasSomeEmptyItems_conversionToLocalItemsIsCorrect() {
//        // given
//        val feed = getMockFeedSomeItemsEmpty()
//
//        // when
//        val localItems = feed.toLocalItems()
//
//        // then
//        verifyListSizeWhenSomeItemsEmpty(localItems)
//        verifyLocalItemsAgainstFeedItems(feed, localItems)
//    }
//
//    @Test
//    fun feedIsEmptyJson_conversionToLocalItemsIsCorrect() {
//        // given
//        val feed = getMockFeedEmptyJson()
//
//        // when
//        val localItems = feed.toLocalItems()
//
//        // then
//        verifyListSizeWhenJsonIsEmpty(localItems)
//    }
//
//    private fun verifyLocalItemsAgainstFeedItems(
//        feed: Feed,
//        localItems: List<LocalItem>
//    ) {
//        feed.cards!!.forEachIndexed lit@{ _, feedItem ->
//            if (feedItem == null) return@lit
//            localItems.forEachIndexed { _, localItem ->
//                feedItem.id?.let {
//                    if (it == localItem.id) {
//                        verifyLocalItemAgainstFeedItem(feedItem, localItem)
//                        return@lit
//                    }
//                }
//            }
//        }
//    }
//
//}