package com.harismexis.breakingbad.datasource

import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
import com.harismexis.breakingbad.framework.datasource.network.data.BreakingBadRemoteDataSource
import com.harismexis.breakingbad.setup.UnitTestSetup
import com.harismexis.breakingbad.utils.ActorRemoteVerificator
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class BreakingBadRemoteDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: BreakingBadRemoteDao
    private var verificator = ActorRemoteVerificator()
    private lateinit var subject: BreakingBadRemoteDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = BreakingBadRemoteDataSource(mockDao)
    }

    @Test
    fun dateSourceRequestsItems_then_daoRequestsItems() {
        // when
        runBlocking {
            // given
            val mockFeed = actorsParser.getMockActorsFeedAllIdsValid()
            Mockito.`when`(mockDao.getActors()).thenReturn(mockFeed)

            // when
            val items = subject.getActors()

            // then
            verify(mockDao, times(1)).getActors()
            verificator.verifyBBCharactersAgainstRemoteItems(items, mockFeed)
        }
    }

}