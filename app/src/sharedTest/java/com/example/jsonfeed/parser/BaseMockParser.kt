package com.example.jsonfeed.parser

import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.datasource.network.PokemonFeed
import com.example.jsonfeed.framework.extensions.toItems
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject

abstract class BaseMockParser {

    companion object {

        const val EXPECTED_NUM_MODELS_ALL_IDS_VALID = 5
        const val EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT = 3
        const val EXPECTED_NUM_MODELS_WHEN_TWO_EMPTY = 3
        const val EXPECTED_NUM_MODELS_WHEN_ONE_ITEM_VALID = 1
        const val EXPECTED_NUM_MODELS_FOR_NO_DATA = 0

        private const val TEST_FILE_FIVE_VALID_ITEMS = "test_data_five_valid_items.json"
        private const val TEST_FILE_FIVE_ITEMS_BUT_TWO_IDS_ABSENT = "test_data_five_items_but_two_ids_absent.json"
        private const val TEST_FILE_FIVE_ITEMS_BUT_TWO_EMPTY = "test_data_five_items_but_two_items_empty.json"
        private const val TEST_FILE_FIVE_ITEMS_BUT_ONE_VALID = "test_data_five_items_but_one_valid.json"
        private const val TEST_FILE_FIVE_ITEMS_ALL_IDS_ABSENT = "test_data_five_items_all_ids_absent.json"
        private const val TEST_FILE_EMPTY_JSON = "test_data_empty_json.json"
        private const val TEST_FILE_EMPTY_DATA_ARRAY = "test_data_empty_data_array.json"
    }

    abstract fun getFileAsString(filePath: String): String

    fun getMockItemValid(): Item = getMockFeedAllIdsValid().toItems()[0]

    fun getMockItemsFromFeedWithAllItemsValid(): List<Item> = getMockFeedAllIdsValid().toItems()
    fun getMockItemsFromFeedWithSomeIdsAbsent(): List<Item> = getMockFeedSomeIdsAbsent().toItems()
    fun getMockItemsFromFeedWithSomeItemsEmpty(): List<Item> = getMockFeedSomeItemsEmpty().toItems()
    fun getMockItemsFromFeedWithOneItemValid(): List<Item> = getMockFeedOneItemValid().toItems()
    fun getMockItemsFromFeedWithAllIdsAbsent(): List<Item> = getMockFeedAllIdsAbsent().toItems()
    fun getMockItemsFromFeedWithEmptyJson(): List<Item> = getMockFeedEmptyJson().toItems()
    fun getMockItemsFromFeedWithEmptyDataArray(): List<Item> = getMockFeedEmptyDataArray().toItems()

    private fun getMockFeedAllIdsValid(): PokemonFeed = getMockPokemonFeed(mockDataAllIdsValid())
    private fun getMockFeedSomeIdsAbsent(): PokemonFeed = getMockPokemonFeed(mockDataSomeIdsAbsent())
    private fun getMockFeedSomeItemsEmpty(): PokemonFeed = getMockPokemonFeed(mockDataSomeItemsEmpty())
    private fun getMockFeedOneItemValid(): PokemonFeed = getMockPokemonFeed(mockDataOneValid())
    private fun getMockFeedAllIdsAbsent(): PokemonFeed = getMockPokemonFeed(mockDataAllIdsAbsent())
    private fun getMockFeedEmptyJson(): PokemonFeed = getMockPokemonFeed(mockDataEmptyJson())
    private fun getMockFeedEmptyDataArray(): PokemonFeed = getMockPokemonFeed(mockDataEmptyDataArray())

    private fun mockDataAllIdsValid(): String = getFileAsString(TEST_FILE_FIVE_VALID_ITEMS)
    private fun mockDataSomeIdsAbsent(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_BUT_TWO_IDS_ABSENT)
    private fun mockDataSomeItemsEmpty(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_BUT_TWO_EMPTY)
    private fun mockDataOneValid(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_BUT_ONE_VALID)
    private fun mockDataAllIdsAbsent(): String = getFileAsString(TEST_FILE_FIVE_ITEMS_ALL_IDS_ABSENT)
    private fun mockDataEmptyJson(): String = getFileAsString(TEST_FILE_EMPTY_JSON)
    private fun mockDataEmptyDataArray(): String = getFileAsString(TEST_FILE_EMPTY_DATA_ARRAY)

    private fun getMockPokemonFeed(text: String): PokemonFeed {
        return convertToFeed(text)
    }

    private inline fun <reified T> convertToFeed(jsonString: String?): T {
        val gson = GsonBuilder().setLenient().create()
        val json: JsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        return Gson().fromJson(json, T::class.java)
    }

}