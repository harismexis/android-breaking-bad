package com.harismexis.breakingbad.datasource

import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDao
import com.harismexis.breakingbad.framework.datasource.database.data.BreakingBadLocalDataSource
import com.harismexis.breakingbad.framework.extensions.actor.toLocalItems
import com.harismexis.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class BreakingBadLocalDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: BreakingBadLocalDao
    private lateinit var subject: BreakingBadLocalDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = BreakingBadLocalDataSource(mockDao)
    }

    @Test
    fun dataSourceInsertsActors_then_daoCallsExpectedMethod() {
        runBlocking {
            // given
            val mockActors = actorsParser.getMockActorsWhenJsonHasAllItemsValid()
            val mockLocalActors = mockActors.toLocalItems()

            // when
            subject.updateActors(mockActors)

            // then
            verify(mockDao, times(1)).insertActors(mockLocalActors)
        }
    }

    @Test
    fun dataSourceRequestsActor_then_daoCallsExpectedMethod() {
        runBlocking {
            // given
            val mockLocalActor = actorsParser.getMockLocalActor()
            val mockLocalActorId = mockLocalActor.char_id
            Mockito.`when`(mockDao.getActorById(mockLocalActorId)).thenReturn(mockLocalActor)

            // when
            val actor = subject.getActor(mockLocalActorId)

            // then
            verify(mockDao, times(1)).getActorById(mockLocalActorId)
        }
    }

    @Test
    fun dataSourceRequestsActors_then_daoCallsExpectedMethod() {
        runBlocking {
            // given
            val mockLocalActors = actorsParser.getMockLocalActorsWhenJsonHasAllItemsValid()
            Mockito.`when`(mockDao.getAllActors()).thenReturn(mockLocalActors)

            // when
            val items = subject.getActors()

            // then
            verify(mockDao, times(1)).getAllActors()
        }
    }

}