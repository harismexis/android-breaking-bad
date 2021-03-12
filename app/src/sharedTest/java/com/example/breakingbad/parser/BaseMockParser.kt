package com.example.breakingbad.parser

import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.framework.datasource.database.BBCharacterLocalItem
import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemoteItem
import com.example.breakingbad.framework.extensions.toLocalItems
import com.example.breakingbad.framework.extensions.toItems
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

abstract class BaseMockParser {

    companion object {

        const val EXPECTED_NUM_BBCHARS_WHEN_ALL_IDS_VALID = 5
        const val EXPECTED_NUM_BBCHARS_WHEN_TWO_IDS_ABSENT = 3
        const val EXPECTED_NUM_BBCHARS_WHEN_TWO_EMPTY = 3
        const val EXPECTED_NUM_BBCHARS_WHEN_NO_DATA = 0

        private const val TEST_FILE_FIVE_VALID_BBCHARS =
            "remote_data_five_valid_bbcharacters.json"
        private const val TEST_FILE_FIVE_BBCHARS_BUT_TWO_IDS_ABSENT =
            "remote_data_five_bbcharacters_two_ids_absent.json"
        private const val TEST_FILE_FIVE_BBCHARS_BUT_TWO_EMPTY =
            "remote_data_five_bbcharacters_two_items_empty.json"
        private const val TEST_FILE_FIVE_BBCHARS_ALL_IDS_ABSENT =
            "remote_data_five_bbcharacters_all_ids_absent.json"
        private const val TEST_FILE_EMPTY_JSON =
            "remote_data_empty.json"
    }

    abstract fun getFileAsString(filePath: String): String

    // local models
    fun getMockBBCharValid(): BBCharacter = getMockBBCharsFeedAllIdsValid().toItems()[0]

    fun getMockBBCharsLocalFromFeedWithAllItemsValid(): List<BBCharacterLocalItem> =
        getMockBBCharsFeedAllIdsValid().toItems().toLocalItems()

    fun getMockBBCharsLocalFromFeedWithSomeIdsAbsent(): List<BBCharacterLocalItem> =
        getMockBBCharsFeedSomeIdsAbsent().toItems().toLocalItems()

    fun getMockBBCharsLocalFromFeedWithAllIdsAbsent(): List<BBCharacterLocalItem> =
        getMockBBCharsFeedAllIdsAbsent().toItems().toLocalItems()

    // base models
    fun getMockBBCharsFromFeedWithAllItemsValid(): List<BBCharacter> =
        getMockBBCharsFeedAllIdsValid().toItems()

    fun getMockBBCharsFromFeedWithSomeIdsAbsent(): List<BBCharacter> =
        getMockBBCharsFeedSomeIdsAbsent().toItems()

    fun getMockBBCharsFromFeedWithSomeItemsEmpty(): List<BBCharacter> =
        getMockBBCharsFeedSomeItemsEmpty().toItems()

    fun getMockBBCharsFromFeedWithAllIdsAbsent(): List<BBCharacter> =
        getMockBBCharsFeedAllIdsAbsent().toItems()

    fun getMockBBCharsFromFeedWithEmptyJsonArray(): List<BBCharacter> =
        getMockBBCharsFeedEmptyJsonArray().toItems()

    // remote models
    fun getMockBBCharsFeedAllIdsValid(): List<BBCharacterRemoteItem> =
        getMockBBCharactersFeed(getMockBBCharsDataAllIdsValid())

    fun getMockBBCharsFeedSomeIdsAbsent(): List<BBCharacterRemoteItem> =
        getMockBBCharactersFeed(getMockBBCharsDataSomeIdsAbsent())

    fun getMockBBCharsFeedSomeItemsEmpty(): List<BBCharacterRemoteItem> =
        getMockBBCharactersFeed(getMockBBCharsDataSomeItemsEmpty())

    fun getMockBBCharsFeedAllIdsAbsent(): List<BBCharacterRemoteItem> =
        getMockBBCharactersFeed(getMockBBCharsDataAllIdsAbsent())

    fun getMockBBCharsFeedEmptyJsonArray(): List<BBCharacterRemoteItem> =
        getMockBBCharactersFeed(getMockBBCharsDataEmptyJsonArray())

    // raw json string
    private fun getMockBBCharsDataAllIdsValid(): String =
        getFileAsString(TEST_FILE_FIVE_VALID_BBCHARS)

    private fun getMockBBCharsDataSomeIdsAbsent(): String =
        getFileAsString(TEST_FILE_FIVE_BBCHARS_BUT_TWO_IDS_ABSENT)

    private fun getMockBBCharsDataSomeItemsEmpty(): String =
        getFileAsString(TEST_FILE_FIVE_BBCHARS_BUT_TWO_EMPTY)

    private fun getMockBBCharsDataAllIdsAbsent(): String =
        getFileAsString(TEST_FILE_FIVE_BBCHARS_ALL_IDS_ABSENT)

    private fun getMockBBCharsDataEmptyJsonArray(): String =
        getFileAsString(TEST_FILE_EMPTY_JSON)

    // utils
    private fun getMockBBCharactersFeed(
        text: String
    ): List<BBCharacterRemoteItem> {
        return convertToBBCharacters(text)
    }

    private fun convertToBBCharacters(jsonFeed: String?): List<BBCharacterRemoteItem> {
        val gson = GsonBuilder().setLenient().create()
        val type: Type = object : TypeToken<ArrayList<BBCharacterRemoteItem>>() {}.type
        return gson.fromJson(jsonFeed, type)
    }

}