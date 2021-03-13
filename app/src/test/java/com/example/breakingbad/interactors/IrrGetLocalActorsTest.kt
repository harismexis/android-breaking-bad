package com.example.breakingbad.interactors

import com.example.breakingbad.data.BreakingBadLocalRepository
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.setup.UnitTestSetup
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
        mockItems = mockParser.getMockBBCharsFromFeedWithAllItemsValid()
        runBlocking {
            Mockito.`when`(mockRepository.getItems()).thenReturn(mockItems)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val items = subject.invoke()

            // then
            verify(mockRepository, times(1)).getItems()
            Assert.assertEquals(mockItems.size, items.size)
        }

}