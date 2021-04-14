package com.harismexis.breakingbad.parser

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor
import com.harismexis.breakingbad.framework.datasource.network.model.RemoteActor
import com.harismexis.breakingbad.framework.extensions.actor.toItems
import com.harismexis.breakingbad.framework.extensions.actor.toLocalItems
import java.lang.reflect.Type
import java.util.*

class ActorsMockParser(private val parser: BaseFileParser) {

    companion object {

        const val EXPECTED_NUM_ACTORS_WHEN_ALL_IDS_VALID = 5
        const val EXPECTED_NUM_ACTORS_WHEN_SOME_IDS_INVALID = 2
        const val EXPECTED_NUM_ACTORS_WHEN_SOME_EMPTY = 3
        const val EXPECTED_NUM_ACTORS_WHEN_NO_DATA = 0

        private const val TEST_FILE_FIVE_VALID_ACTORS =
            "remote_data_5_valid_actors.json"
        private const val TEST_FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID =
            "remote_data_5_actors_3_ids_invalid.json"
        private const val TEST_FILE_FIVE_ACTORS_BUT_TWO_EMPTY =
            "remote_data_5_actors_2_items_empty.json"
        private const val TEST_FILE_FIVE_ACTORS_ALL_IDS_INVALID =
            "remote_data_5_actors_all_ids_invalid.json"
        private const val TEST_FILE_EMPTY_JSON =
            "remote_data_empty.json"
    }

    // local models
    fun getMockActorLocal(): Actor = getMockActorsFeedAllIdsValid().toItems()[0]

    fun getMockActorsLocalFromFeedWithAllItemsValid(): List<LocalActor> =
        getMockActorsFeedAllIdsValid().toItems().toLocalItems()

    fun getMockActorsLocalFromFeedWithSomeIdsInvalid(): List<LocalActor> =
        getMockActorsFeedSomeIdsInvalid().toItems().toLocalItems()

    fun getMockActorsLocalFromFeedWithAllIdsInvalid(): List<LocalActor> =
        getMockActorsFeedAllIdsInvalid().toItems().toLocalItems()

    // base models
    fun getMockActorsFromFeedWithAllItemsValid(): List<Actor> =
        getMockActorsFeedAllIdsValid().toItems()

    fun getMockActorsFromFeedWithSomeIdsInvalid(): List<Actor> =
        getMockActorsFeedSomeIdsInvalid().toItems()

    fun getMockActorsFromFeedWithSomeItemsEmpty(): List<Actor> =
        getMockBBCharsFeedSomeItemsEmpty().toItems()

    fun getMockActorsFromFeedWithAllIdsInvalid(): List<Actor> =
        getMockActorsFeedAllIdsInvalid().toItems()

    fun getMockActorsFromFeedWithEmptyJsonContent(): List<Actor> =
        getMockActorsFeedEmptyJsonArray().toItems()

    // remote models
    fun getMockActorsFeedAllIdsValid(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataAllIdsValid())

    fun getMockActorsFeedSomeIdsInvalid(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataSomeIdsInvalid())

    fun getMockBBCharsFeedSomeItemsEmpty(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataSomeItemsEmpty())

    fun getMockActorsFeedAllIdsInvalid(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataAllIdsInvalid())

    fun getMockActorsFeedEmptyJsonArray(): List<RemoteActor> =
        getMockActorsFeed(getMockActorsDataEmptyJsonArray())

    // raw json string
    private fun getMockActorsDataAllIdsValid(): String =
        parser.getFileAsString(TEST_FILE_FIVE_VALID_ACTORS)

    private fun getMockActorsDataSomeIdsInvalid(): String =
        parser.getFileAsString(TEST_FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID)

    private fun getMockActorsDataSomeItemsEmpty(): String =
        parser.getFileAsString(TEST_FILE_FIVE_ACTORS_BUT_TWO_EMPTY)

    private fun getMockActorsDataAllIdsInvalid(): String =
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