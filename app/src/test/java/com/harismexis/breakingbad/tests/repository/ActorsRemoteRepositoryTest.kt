package com.harismexis.breakingbad.tests.repository

import com.harismexis.breakingbad.base.BaseUnitTest
import com.harismexis.breakingbad.framework.data.network.api.BreakingBadApi
import com.harismexis.breakingbad.framework.data.network.repository.ActorsRemoteRepository
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.FILE_EMPTY_JSON
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.FILE_FIVE_ACTORS_ALL_IDS_INVALID
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.FILE_FIVE_ACTORS_BUT_TWO_EMPTY
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.FILE_FIVE_VALID_ACTORS
import com.harismexis.breakingbad.util.enqueueResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class ActorsRemoteRepositoryTest : BaseUnitTest() {

    private lateinit var subject: ActorsRemoteRepository

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

    @Before
    fun doBefore() {
        initMocks()
        subject = ActorsRemoteRepository(api)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponseStatus200(filePath: String) {
        mockWebServer.enqueueResponse(filePath, 200)
    }

    @Test
    fun whenJsonHasAllIdsValid_actorsItemsAreCorrect() {
        val expected = mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid()
        enqueueResponseStatus200(FILE_FIVE_VALID_ACTORS)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun whenJsonHasSomeInvalidIds_actorsItemsAreCorrect() {
        val expected = mockActorsProvider.getMockActorsWhenJsonHasSomeInvalidIds()
        enqueueResponseStatus200(FILE_FIVE_ACTORS_BUT_THREE_IDS_INVALID)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun whenJsonHasSomeEmptyItems_actorsItemsAreCorrect() {
        val expected = mockActorsProvider.getMockActorsWhenJsonHasSomeEmptyItems()
        enqueueResponseStatus200(FILE_FIVE_ACTORS_BUT_TWO_EMPTY)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun whenJsonHasAllIdsInvalid_actorsItemsAreEmpty() {
        val expected = mockActorsProvider.getMockActorsWhenJsonHasAllIdsInvalid()
        enqueueResponseStatus200(FILE_FIVE_ACTORS_ALL_IDS_INVALID)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun whenJsonIsEmpty_actorsItemsAreEmpty() {
        val expected = mockActorsProvider.getMockActorsWhenJsonIsEmpty()
        enqueueResponseStatus200(FILE_EMPTY_JSON)
        runBlocking {
            val actual = subject.getActors()
            assertEquals(expected, actual)
        }
    }


}