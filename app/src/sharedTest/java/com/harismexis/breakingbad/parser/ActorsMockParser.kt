package com.harismexis.breakingbad.parser

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteActor
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import com.harismexis.breakingbad.framework.extensions.actor.toLocalItems
import java.lang.reflect.Type

class ActorsMockParser(private val parser: BaseFileParser) {

    companion object {

        const val EXPECTED_NUM_ACTORS_WHEN_ALL_IDS_VALID = 5
        const val EXPECTED_NUM_ACTORS_WHEN_SOME_IDS_INVALID = 3
        const val EXPECTED_NUM_ACTORS_WHEN_SOME_EMPTY = 3
        const val EXPECTED_NUM_ACTORS_WHEN_NO_DATA = 0

        private const val TEST_FILE_FIVE_VALID_ACTORS =
            "remote_data_five_valid_bbcharacters.json"
        private const val TEST_FILE_FIVE_ACTORS_BUT_TWO_IDS_INVALID =
            "remote_data_five_bbcharacters_two_ids_absent.json"
        private const val TEST_FILE_FIVE_ACTORS_BUT_TWO_EMPTY =
            "remote_data_five_bbcharacters_two_items_empty.json"
        private const val TEST_FILE_FIVE_ACTORS_ALL_IDS_INVALID =
            "remote_data_five_bbcharacters_all_ids_absent.json"
        private const val TEST_FILE_EMPTY_JSON =
            "remote_data_empty.json"
    }

    // local models
    fun getMockActorLocal(): Actor = getMockActorsFeedAllIdsValid().toItems()[0]

    fun getMockActorsLocalFromFeedWithAllItemsValid(): List<LocalActor> =
        getMockActorsFeedAllIdsValid().toItems().toLocalItems()

    fun getMockActorsLocalFromFeedWithSomeIdsInvalid(): List<LocalActor> =
        getMockActorsFeedSomeIdsAbsent().toItems().toLocalItems()

    fun getMockActorsLocalFromFeedWithAllIdsInvalid(): List<LocalActor> =
        getMockActorsFeedAllIdsAbsent().toItems().toLocalItems()

    // base models
    fun getMockActorsFromFeedWithAllItemsValid(): List<Actor> =
        getMockActorsFeedAllIdsValid().toItems()

    fun getMockActorsFromFeedWithSomeIdsAbsent(): List<Actor> =
        getMockActorsFeedSomeIdsAbsent().toItems()

    fun getMockActorsFromFeedWithSomeItemsEmpty(): List<Actor> =
        getMockBBCharsFeedSomeItemsEmpty().toItems()

    fun getMockActorsFromFeedWithAllIdsInvalid(): List<Actor> =
        getMockActorsFeedAllIdsAbsent().toItems()

    fun getMockActorsFromFeedWithEmptyJsonContent(): List<Actor> =
        getMockActorsFeedEmptyJsonArray().toItems()

    // remote models
    fun getMockActorsFeedAllIdsValid(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataAllIdsValid())

    fun getMockActorsFeedSomeIdsAbsent(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataSomeIdsAbsent())

    fun getMockBBCharsFeedSomeItemsEmpty(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataSomeItemsEmpty())

    fun getMockActorsFeedAllIdsAbsent(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataAllIdsAbsent())

    fun getMockActorsFeedEmptyJsonArray(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataEmptyJsonArray())

    // raw json string
    private fun getMockActorsDataAllIdsValid(): String =
        parser.getFileAsString(TEST_FILE_FIVE_VALID_ACTORS)

    private fun getMockActorsDataSomeIdsAbsent(): String =
        parser.getFileAsString(TEST_FILE_FIVE_ACTORS_BUT_TWO_IDS_INVALID)

    private fun getMockActorsDataSomeItemsEmpty(): String =
        parser.getFileAsString(TEST_FILE_FIVE_ACTORS_BUT_TWO_EMPTY)

    private fun getMockActorsDataAllIdsAbsent(): String =
        parser.getFileAsString(TEST_FILE_FIVE_ACTORS_ALL_IDS_INVALID)

    private fun getMockActorsDataEmptyJsonArray(): String =
        parser.getFileAsString(TEST_FILE_EMPTY_JSON)

    // utils
    private fun getMockActorsFeed(
        text: String
    ): List<RemoteActor> {
        return convertToRemoteActors(text)
    }

    private fun convertToRemoteActors(jsonFeed: String?): List<RemoteActor> {
        val gson = GsonBuilder().setLenient().create()
        val type: Type = object : TypeToken<ArrayList<RemoteActor>>() {}.type
        return gson.fromJson(jsonFeed, type)
    }

}