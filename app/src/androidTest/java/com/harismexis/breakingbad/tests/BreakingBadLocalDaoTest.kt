package com.harismexis.breakingbad.tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harismexis.breakingbad.framework.datasource.database.BreakingBadDatabase
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor
import com.harismexis.breakingbad.parser.ActorsMockParser.Companion.EXPECTED_NUM_ACTORS_WHEN_ALL_IDS_VALID
import com.harismexis.breakingbad.parser.ActorsMockParser.Companion.EXPECTED_NUM_ACTORS_WHEN_NO_DATA
import com.harismexis.breakingbad.parser.ActorsMockParser.Companion.EXPECTED_NUM_ACTORS_WHEN_SOME_IDS_INVALID
import com.harismexis.breakingbad.setup.base.InstrumentedTestSetup
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BreakingBadLocalDaoTest: InstrumentedTestSetup() {

    private lateinit var dao: BreakingBadLocalDao
    private lateinit var database: BreakingBadDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, BreakingBadDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.getDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromRemoteFeedWithAllItemsValid_then_expectedItemsRetrieved() = runBlocking {
        // given
        val localItems = actorsParser.getMockActorsLocalFromFeedWithAllItemsValid()

        // when
        dao.insertActors(localItems)
        val retrievedLocalItems = dao.getAllActors()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_ACTORS_WHEN_ALL_IDS_VALID)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromRemoteFeedWithSomeIdsAbsent_then_expectedItemsRetrieved() = runBlocking {
        // given
        val localItems = actorsParser.getMockActorsLocalFromFeedWithSomeIdsInvalid()

        // when
        dao.insertActors(localItems)
        val retrievedLocalItems = dao.getAllActors()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_ACTORS_WHEN_SOME_IDS_INVALID)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromFeedWithAllIdsAbsent_then_noItemsRetrieved() = runBlocking {
        // given
        val localItems = actorsParser.getMockActorsLocalFromFeedWithAllIdsInvalid()

        // when
        dao.insertActors(localItems)
        val retrievedLocalItems = dao.getAllActors()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_ACTORS_WHEN_NO_DATA)
    }

    private fun verifyActualAgainstExpected(
        actual: List<LocalActor?>,
        expected: List<LocalActor>,
        expectedNumberOfItems: Int
    ) {
        Assert.assertNotNull(actual)
        Assert.assertNotNull(expected)
        Assert.assertEquals(expected.size, actual.size)
        Assert.assertEquals(expected, actual)
        Assert.assertEquals(expectedNumberOfItems, actual.size)
        Assert.assertEquals(expectedNumberOfItems, expected.size)
    }

}
