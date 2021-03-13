package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBActorLocalRepository
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class IrrStoreBBActorsTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: BBActorLocalRepository

    private lateinit var mockItems: List<BBActor>
    private lateinit var subject: IrrStoreBBActors

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = IrrStoreBBActors(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockBBCharsFromFeedWithAllItemsValid()
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            subject.invoke(mockItems)

            // then
            verify(mockRepository, times(1)).insertItems(mockItems)
        }

}