package com.example.breakingbad.parser

import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.datasource.database.LocalActor
import com.example.breakingbad.framework.datasource.network.model.RemoteActor
import com.example.breakingbad.framework.extensions.actor.toItems
import com.example.breakingbad.framework.extensions.actor.toLocalItems
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
    fun getMockBBCharValid(): Actor = getMockBBCharsFeedAllIdsValid().toItems()[0]

    fun getMockBBCharsLocalFromFeedWithAllItemsValid(): List<LocalActor> =
        getMockBBCharsFeedAllIdsValid().toItems().toLocalItems()

    fun getMockBBCharsLocalFromFeedWithSomeIdsAbsent(): List<LocalActor> =
        getMockBBCharsFeedSomeIdsAbsent().toItems().toLocalItems()

    fun getMockBBCharsLocalFromFeedWithAllIdsAbsent(): List<LocalActor> =
        getMockBBCharsFeedAllIdsAbsent().toItems().toLocalItems()

    // base models
    fun getMockBBCharsFromFeedWithAllItemsValid(): List<Actor> =
        getMockBBCharsFeedAllIdsValid().toItems()

    fun getMockBBCharsFromFeedWithSomeIdsAbsent(): List<Actor> =
        getMockBBCharsFeedSomeIdsAbsent().toItems()

    fun getMockBBCharsFromFeedWithSomeItemsEmpty(): List<Actor> =
        getMockBBCharsFeedSomeItemsEmpty().toItems()

    fun getMockBBCharsFromFeedWithAllIdsAbsent(): List<Actor> =
        getMockBBCharsFeedAllIdsAbsent().toItems()

    fun getMockBBCharsFromFeedWithEmptyJsonArray(): List<Actor> =
        getMockBBCharsFeedEmptyJsonArray().toItems()

    // remote models
    fun getMockBBCharsFeedAllIdsValid(): List<RemoteActor> =
        getMockBBCharactersFeed(getMockBBCharsDataAllIdsValid())

    fun getMockBBCharsFeedSomeIdsAbsent(): List<RemoteActor> =
        getMockBBCharactersFeed(getMockBBCharsDataSomeIdsAbsent())

    fun getMockBBCharsFeedSomeItemsEmpty(): List<RemoteActor> =
        getMockBBCharactersFeed(getMockBBCharsDataSomeItemsEmpty())

    fun getMockBBCharsFeedAllIdsAbsent(): List<RemoteActor> =
        getMockBBCharactersFeed(getMockBBCharsDataAllIdsAbsent())

    fun getMockBBCharsFeedEmptyJsonArray(): List<RemoteActor> =
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
    ): List<RemoteActor> {
        return convertToBBCharacters(text)
    }

    private fun convertToBBCharacters(jsonFeed: String?): List<RemoteActor> {
        val gson = GsonBuilder().setLenient().create()
        val type: Type = object : TypeToken<ArrayList<RemoteActor>>() {}.type
        return gson.fromJson(jsonFeed, type)
    }

}