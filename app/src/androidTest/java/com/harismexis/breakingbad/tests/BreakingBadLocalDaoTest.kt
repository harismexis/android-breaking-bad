package com.harismexis.breakingbad.tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harismexis.breakingbad.base.BaseInstrumentedTest
import com.harismexis.breakingbad.framework.data.database.dao.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.data.database.db.BreakingBadDatabase
import com.harismexis.breakingbad.framework.data.database.table.LocalActor
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_ALL_IDS_VALID
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_NO_DATA
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_SOME_IDS_INVALID
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BreakingBadLocalDaoTest: BaseInstrumentedTest() {

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
    fun saveItemsFromResponseWithAllItemsValid_expectedItemsSaved() = runBlocking {
        // given
        val itemsToSave = mockActorsProvider.getMockLocalActorsWhenJsonHasAllItemsValid()

        // when
        dao.insertActors(itemsToSave)
        val savedItems = dao.getAllActors()

        // then
        verifyLists(savedItems!!, itemsToSave, NUM_ACTORS_WHEN_ALL_IDS_VALID)
    }

    @Test
    @Throws(Exception::class)
    fun saveItemsFromResponseWithSomeIdsAbsent_expectedItemsSaved() = runBlocking {
        // given
        val itemsToSave = mockActorsProvider.getMockLocalActorsWhenJsonHasSomeInvalidIds()

        // when
        dao.insertActors(itemsToSave)
        val savedItems = dao.getAllActors()

        // then
        verifyLists(savedItems!!, itemsToSave, NUM_ACTORS_WHEN_SOME_IDS_INVALID)
    }

    @Test
    @Throws(Exception::class)
    fun saveItemsFromResponseWithAllIdsAbsent_noItemSaved() = runBlocking {
        // given
        val itemsToSave = mockActorsProvider.getMockLocalActorsWhenJsonHasAllIdsInvalid()

        // when
        dao.insertActors(itemsToSave)
        val savedItems = dao.getAllActors()

        // then
        verifyLists(savedItems!!, itemsToSave, NUM_ACTORS_WHEN_NO_DATA)
    }

    private fun verifyLists(
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
