package com.harismexis.breakingbad.interactors

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActors
import com.harismexis.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class IrrGetLocalActorsTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: BreakingBadLocalRepository

    private lateinit var mockItems: List<Actor>
    private lateinit var subject: IrrGetLocalActors

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = IrrGetLocalActors(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockActorsFromFeedWithAllItemsValid()
        runBlocking {
            Mockito.`when`(mockRepository.getActors()).thenReturn(mockItems)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val items = subject()

            // then
            verify(mockRepository, times(1)).getActors()
            Assert.assertEquals(mockItems.size, items.size)
        }

}