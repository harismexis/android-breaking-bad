package com.example.jsonfeed.base//package com.example.jsonfeed.framework.extensions
//
//import org.junit.Test
//
//class LocalExtensionTest : BaseTestSetup() {
//
//    @Test
//    fun localItemsAreConvertedToUiModels_uiModelsAreCorrect() {
//        // given
//        val localItems = getMockLocalItemsFromFeedAllIdsValid()
//
//        // when
//        val uiModels = localItems.toUiModels()
//
//        // then
//        verifyListsHaveSameSize(localItems, uiModels)
//        verifyListSizeWhenAllIdsValid(localItems)
//        verifyListSizeWhenAllIdsValid(uiModels)
//
//        localItems.forEachIndexed { index, localItem ->
//            val uiModel = uiModels[index]
//            verifyUiModelAgainstLocalItem(localItem, uiModel)
//        }
//    }
//
//}