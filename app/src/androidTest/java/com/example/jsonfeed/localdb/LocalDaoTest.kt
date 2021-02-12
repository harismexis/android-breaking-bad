package com.example.jsonfeed.localdb

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jsonfeed.base.InstrumentedTestSetup
import com.example.jsonfeed.framework.datasource.db.PokemonLocalDao
import com.example.jsonfeed.framework.datasource.db.PokemonDatabase
import com.example.jsonfeed.framework.extensions.toPokemonEntities
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_ALL_IDS_VALID
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalDaoTest: InstrumentedTestSetup() {

    private lateinit var dao: PokemonLocalDao
    private lateinit var database: PokemonDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, PokemonDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.getLocalDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun savingItems_correctItemsAreRetrieved() = runBlocking {
        // given
        val savedItems = mockParser.getMockItemsFromFeedWithAllItemsValid().toPokemonEntities()

        // when
        dao.insertItems(savedItems)
        val retrievedItems = dao.getAllItems()

        // then
        Assert.assertNotNull(retrievedItems)
        Assert.assertNotEquals(0, retrievedItems!!.size)
        Assert.assertEquals(savedItems.size, retrievedItems.size)
        Assert.assertEquals(savedItems, retrievedItems)
        Assert.assertEquals(EXPECTED_NUM_MODELS_ALL_IDS_VALID, retrievedItems.size)
    }

    @Test
    @Throws(Exception::class)
    fun savingFeedItemsWithNoIds_noLocalItemsRetrieved() = runBlocking {
        // given
        val savedItems = mockParser.getMockItemsFromFeedWithAllIdsAbsent().toPokemonEntities()

        // when
        dao.insertItems(savedItems)
        val retrievedItems = dao.getAllItems()

        // then
        Assert.assertNotNull(retrievedItems)
        Assert.assertEquals(0, retrievedItems!!.size)
        Assert.assertEquals(savedItems.size, retrievedItems.size)
        Assert.assertEquals(savedItems, retrievedItems)
    }

}
