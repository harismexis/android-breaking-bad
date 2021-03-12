package com.example.breakingbad.tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.breakingbad.framework.datasource.database.BBDatabase
import com.example.breakingbad.framework.datasource.database.BBLocalDao
import com.example.breakingbad.framework.datasource.database.BBCharacterLocal
import com.example.breakingbad.setup.base.InstrumentedTestSetup
import com.example.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_ALL_IDS_VALID
import com.example.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_NO_DATA
import com.example.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_TWO_IDS_ABSENT
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BBLocalDaoTest: InstrumentedTestSetup() {

    private lateinit var dao: BBLocalDao
    private lateinit var database: BBDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, BBDatabase::class.java)
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
        dao.insertItems(localItems)
        val retrievedLocalItems = dao.getAllItems()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_BBCHARS_WHEN_ALL_IDS_VALID)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromRemoteFeedWithSomeIdsAbsent_then_expectedItemsRetrieved() = runBlocking {
        // given
        val localItems = mockParser.getMockBBCharsLocalFromFeedWithSomeIdsAbsent()

        // when
        dao.insertItems(localItems)
        val retrievedLocalItems = dao.getAllItems()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_BBCHARS_WHEN_TWO_IDS_ABSENT)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromFeedWithAllIdsAbsent_then_noItemsRetrieved() = runBlocking {
        // given
        val localItems = mockParser.getMockBBCharsLocalFromFeedWithAllIdsAbsent()

        // when
        dao.insertItems(localItems)
        val retrievedLocalItems = dao.getAllItems()

        // then
        verifyActualAgainstExpected(retrievedLocalItems!!, localItems, EXPECTED_NUM_BBCHARS_WHEN_NO_DATA)
    }

    private fun verifyActualAgainstExpected(
        actual: List<BBCharacterLocal?>,
        expected: List<BBCharacterLocal>,
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
