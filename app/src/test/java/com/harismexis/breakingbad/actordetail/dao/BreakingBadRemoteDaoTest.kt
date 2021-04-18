package com.harismexis.breakingbad.actordetail.dao

import com.harismexis.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
import com.harismexis.breakingbad.parser.MockActorsParser.Companion.FILE_EMPTY_JSON
import com.harismexis.breakingbad.parser.MockActorsParser.Companion.FILE_FIVE_ACTORS_ALL_IDS_INVALID
import com.harismexis.breakingbad.parser.MockActorsParser.Companion.FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID
import com.harismexis.breakingbad.parser.MockActorsParser.Companion.FILE_FIVE_ACTORS_BUT_TWO_EMPTY
import com.harismexis.breakingbad.parser.MockActorsParser.Companion.FILE_FIVE_VALID_ACTORS
import com.harismexis.breakingbad.setup.UnitTestSetup
import com.harismexis.breakingbad.setup.enqueueResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class BreakingBadRemoteDaoTest : UnitTestSetup() {

    private lateinit var subject: BreakingBadRemoteDao

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BreakingBadApi::class.java)

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = BreakingBadRemoteDao(api)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun test_1() {
        val expected = actorsParser.getMockRemoteActorsWhenJsonHasAllIdsValid()
        mockWebServer.enqueueResponse(FILE_FIVE_VALID_ACTORS, 200)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun test_2() {
        val expected = actorsParser.getMockRemoteActorsWhenJsonHasSomeInvalidIds()
        mockWebServer.enqueueResponse(FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID, 200)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun test_3() {
        val expected = actorsParser.getMockRemoteActorsWhenJsonHasSomeEmptyItems()
        mockWebServer.enqueueResponse(FILE_FIVE_ACTORS_BUT_TWO_EMPTY, 200)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun test_4() {
        val expected = actorsParser.getMockRemoteActorsWhenJsonHasAllIdsInvalid()
        mockWebServer.enqueueResponse(FILE_FIVE_ACTORS_ALL_IDS_INVALID, 200)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun test_5() {
        val expected = actorsParser.getMockRemoteActorsWhenJsonIsEmpty()
        mockWebServer.enqueueResponse(FILE_EMPTY_JSON, 200)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }


}