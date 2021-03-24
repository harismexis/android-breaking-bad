package com.harismexis.breakingbad.tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harismexis.breakingbad.framework.datasource.database.BreakingBadDatabase
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.datasource.database.table.LocalActor
import com.harismexis.breakingbad.setup.base.InstrumentedTestSetup
import com.harismexis.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_ALL_IDS_VALID
import com.harismexis.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_NO_DATA
import com.harismexis.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_TWO_IDS_ABSENT
import kotlinx.coroutines.runBlocking
import org.junit.*
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
        val localItems = mockParser.getMockBBCharsLocalFromFeedWithAllItemsValid()

        // when
        dao.insertActors(localItems)
        val retrievedLocalItems = dao.getAllActors()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_BBCHARS_WHEN_ALL_IDS_VALID)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromRemoteFeedWithSomeIdsAbsent_then_expectedItemsRetrieved() = runBlocking {
        // given
        val localItems = mockParser.getMockBBCharsLocalFromFeedWithSomeIdsAbsent()

        // when
        dao.insertActors(localItems)
        val retrievedLocalItems = dao.getAllActors()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_BBCHARS_WHEN_TWO_IDS_ABSENT)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromFeedWithAllIdsAbsent_then_noItemsRetrieved() = runBlocking {
        // given
        val localItems = mockParser.getMockBBCharsLocalFromFeedWithAllIdsAbsent()

        // when
        dao.insertActors(localItems)
        val retrievedLocalItems = dao.getAllActors()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_BBCHARS_WHEN_NO_DATA)
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
