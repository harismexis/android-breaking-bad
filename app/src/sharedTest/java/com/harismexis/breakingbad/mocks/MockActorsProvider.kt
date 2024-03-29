package com.harismexis.breakingbad.mocks

import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.framework.data.database.table.LocalActor
import com.harismexis.breakingbad.framework.data.database.table.toLocalItems
import com.harismexis.breakingbad.framework.data.network.model.RemoteActor
import com.harismexis.breakingbad.framework.data.network.model.toItems
import com.harismexis.breakingbad.reader.BaseFileReader
import com.harismexis.breakingbad.util.jsonToObject

class MockActorsProvider(private val reader: BaseFileReader) {

    companion object {

        const val NUM_ACTORS_WHEN_ALL_IDS_VALID = 5
        const val NUM_ACTORS_WHEN_SOME_IDS_INVALID = 2
        const val NUM_ACTORS_WHEN_SOME_EMPTY = 3
        const val NUM_ACTORS_WHEN_NO_DATA = 0
        const val NUM_ACTORS_WHEN_SEARCH_BY_NAME_LIKE_WALTER = 2
        const val NUM_ACTORS_WHEN_SEARCH_BY_NAME_LIKE_SALA = 4

        const val FILE_FIVE_VALID_ACTORS =
            "remote-5-valid-actors.json"
        const val FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID =
            "remote-5-actors-3-ids-invalid.json"
        const val FILE_FIVE_ACTORS_BUT_TWO_EMPTY =
            "remote-5-actors-2-items-empty.json"
        const val FILE_FIVE_ACTORS_ALL_IDS_INVALID =
            "remote-5-actors-all-ids-invalid.json"
        const val FILE_EMPTY_JSON =
            "remote-empty.json"
        const val FILE_SEARCH_ACTORS_BY_NAME_LIKE_WALTER =
            "remote-search-actors-by-name-like-walter.json"
        const val FILE_SEARCH_ACTORS_BY_NAME_LIKE_SALA =
            "remote-search-actors-by-name-like-sala.json"
    }

    // local models
    fun getMockLocalActor(): LocalActor = getMockLocalActorsWhenJsonHasAllItemsValid()[0]

    fun getMockLocalActorsWhenJsonHasAllItemsValid(): List<LocalActor> =
        getMockActorsWhenJsonHasAllItemsValid().toLocalItems()

    fun getMockLocalActorsWhenJsonHasSomeInvalidIds(): List<LocalActor> =
        getMockActorsWhenJsonHasSomeInvalidIds().toLocalItems()

    fun getMockLocalActorsWhenJsonHasAllIdsInvalid(): List<LocalActor> =
        getMockActorsWhenJsonHasAllIdsInvalid().toLocalItems()

    fun getMockLocalActorsSearchByNameLikeWalter(): List<LocalActor> =
        getMockActorsSearchByNameLikeWalter().toLocalItems()

    fun getMockLocalActorsSearchByNameLikeSala(): List<LocalActor> =
        getMockActorsSearchByNameLikeSala().toLocalItems()

    // core models
    fun getMockActor(): Actor = getMockActorsWhenJsonHasAllItemsValid()[0]

    fun getMockActorsWhenJsonHasAllItemsValid(): List<Actor> =
        getMockRemoteActorsWhenJsonHasAllIdsValid().toItems()

    fun getMockActorsWhenJsonHasSomeInvalidIds(): List<Actor> =
        getMockRemoteActorsWhenJsonHasSomeInvalidIds().toItems()

    fun getMockActorsWhenJsonHasSomeEmptyItems(): List<Actor> =
        getMockRemoteActorsWhenJsonHasSomeEmptyItems().toItems()

    fun getMockActorsWhenJsonHasAllIdsInvalid(): List<Actor> =
        getMockRemoteActorsWhenJsonHasAllIdsInvalid().toItems()

    fun getMockActorsWhenJsonIsEmpty(): List<Actor> =
        getMockRemoteActorsWhenJsonIsEmpty().toItems()

    fun getMockActorsSearchByNameLikeWalter(): List<Actor> =
        getMockRemoteActorsSearchByNameLikeWalter().toItems()

    fun getMockActorsSearchByNameLikeSala(): List<Actor> =
        getMockRemoteActorsSearchByNameLikeSala().toItems()

    // remote models
    fun getMockRemoteActorsWhenJsonHasAllIdsValid(): List<RemoteActor> =
        getMockRemoteActors(getMockDataAllIdsValid())

    fun getMockRemoteActorsWhenJsonHasSomeInvalidIds(): List<RemoteActor> =
        getMockRemoteActors(getMockDataSomeIdsInvalid())

    fun getMockRemoteActorsWhenJsonHasSomeEmptyItems(): List<RemoteActor> =
        getMockRemoteActors(getMockDataSomeItemsEmpty())

    fun getMockRemoteActorsWhenJsonHasAllIdsInvalid(): List<RemoteActor> =
        getMockRemoteActors(getMockDataAllIdsInvalid())

    fun getMockRemoteActorsWhenJsonIsEmpty(): List<RemoteActor> =
        getMockRemoteActors(getMockDataEmptyJsonArray())

    fun getMockRemoteActorsSearchByNameLikeWalter(): List<RemoteActor> =
        getMockRemoteActors(getMockDataSearchByNameLikeWalter())

    fun getMockRemoteActorsSearchByNameLikeSala(): List<RemoteActor> =
        getMockRemoteActors(getMockDataSearchByNameLikeSala())

    // raw json string
    private fun getMockDataAllIdsValid(): String =
        reader.getFileAsString(FILE_FIVE_VALID_ACTORS)

    private fun getMockDataSomeIdsInvalid(): String =
        reader.getFileAsString(FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID)

    private fun getMockDataSomeItemsEmpty(): String =
        reader.getFileAsString(FILE_FIVE_ACTORS_BUT_TWO_EMPTY)

    private fun getMockDataAllIdsInvalid(): String =
        reader.getFileAsString(FILE_FIVE_ACTORS_ALL_IDS_INVALID)

    private fun getMockDataEmptyJsonArray(): String =
        reader.getFileAsString(FILE_EMPTY_JSON)

    private fun getMockDataSearchByNameLikeWalter(): String =
        reader.getFileAsString(FILE_SEARCH_ACTORS_BY_NAME_LIKE_WALTER)

    private fun getMockDataSearchByNameLikeSala(): String =
        reader.getFileAsString(FILE_SEARCH_ACTORS_BY_NAME_LIKE_SALA)

    // utils
    private fun getMockRemoteActors(
        text: String
    ): List<RemoteActor> {
        return jsonToObject(text)
    }

}